/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.aspectj.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.PerClauseKind;

import org.springframework.aop.framework.AopConfigException;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;

/**
 * Abstract base class for factories that can create Spring AOP Advisors
 * given AspectJ classes from classes honoring the AspectJ 5 annotation syntax.
 *
 * <p>This class handles annotation parsing and validation functionality.
 * It does not actually generate Spring AOP Advisors, which is deferred to subclasses.
 *
 * @author Rod Johnson
 * @author Adrian Colyer
 * @author Juergen Hoeller
 * @since 2.0
 */
public abstract class AbstractAspectJAdvisorFactory implements AspectJAdvisorFactory {

	private static final String AJC_MAGIC = "ajc$";

	/**
	 * 切面注解类集合
	 */
	private static final Class<?>[] ASPECTJ_ANNOTATION_CLASSES = new Class<?>[] {
			Pointcut.class, Around.class, Before.class, After.class, AfterReturning.class, AfterThrowing.class};


	/** Logger available to subclasses. */
	protected final Log logger = LogFactory.getLog(getClass());

	protected final ParameterNameDiscoverer parameterNameDiscoverer = new AspectJAnnotationParameterNameDiscoverer();


	/**
	 * We consider something to be an AspectJ aspect suitable for use by the Spring AOP system
	 * if it has the @Aspect annotation, and was not compiled by ajc. The reason for this latter test
	 * is that aspects written in the code-style (AspectJ language) also have the annotation present
	 * when compiled by ajc with the -1.5 flag, yet they cannot be consumed by Spring AOP.
	 * 我们认为某些东西是适合 Spring AOP 系统使用的 AspectJ 方面，
	 * 如果它有 @Aspect 注释，并且不是由 ajc 编译的。
	 * 后一个测试的原因是，以代码风格（AspectJ language）编写的切面在由带有 -1.5 标志的 ajc 编译时也存在注释，但它们不能被 Spring AOP 使用。
	 */
	@Override
	public boolean isAspect(Class<?> clazz) {
		return (hasAspectAnnotation(clazz) && !compiledByAjc(clazz));
	}

	/**
	 * 是否被@Aspect注解修饰
	 * @param clazz
	 * @return
	 */
	private boolean hasAspectAnnotation(Class<?> clazz) {
		return (AnnotationUtils.findAnnotation(clazz, Aspect.class) != null);
	}

	/**
	 * We need to detect this as "code-style" AspectJ aspects should not be
	 * interpreted by Spring AOP.
	 * 我们需要将此检测为“代码样式”AspectJ 切面不应该被 Spring AOP 解释。
	 */
	private boolean compiledByAjc(Class<?> clazz) {
		// The AJTypeSystem goes to great lengths to provide a uniform appearance between code-style and
		// annotation-style aspects. Therefore there is no 'clean' way to tell them apart. Here we rely on
		// an implementation detail of the AspectJ compiler.
		// AJTypeSystem 竭尽全力在代码风格和注释风格方面提供统一的外观。
		// 因此，没有“干净”的方法来区分它们。这里我们依赖于 AspectJ 编译器的实现细节。
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().startsWith(AJC_MAGIC)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void validate(Class<?> aspectClass) throws AopConfigException {
		// If the parent has the annotation and isn't abstract it's an error
		// 如果父级有@Aspect注解并且不是抽象的，那就是一个错误
		if (aspectClass.getSuperclass().getAnnotation(Aspect.class) != null &&
				!Modifier.isAbstract(aspectClass.getSuperclass().getModifiers())) {
			throw new AopConfigException("[" + aspectClass.getName() + "] cannot extend concrete aspect [" +
					aspectClass.getSuperclass().getName() + "]");
		}

		AjType<?> ajType = AjTypeSystem.getAjType(aspectClass);
		if (!ajType.isAspect()) {
			throw new NotAnAtAspectException(aspectClass);
		}
		if (ajType.getPerClause().getKind() == PerClauseKind.PERCFLOW) {
			throw new AopConfigException(aspectClass.getName() + " uses percflow instantiation model: " +
					"This is not supported in Spring AOP.");
		}
		if (ajType.getPerClause().getKind() == PerClauseKind.PERCFLOWBELOW) {
			throw new AopConfigException(aspectClass.getName() + " uses percflowbelow instantiation model: " +
					"This is not supported in Spring AOP.");
		}
	}

	/**
	 * Find and return the first AspectJ annotation on the given method
	 * (there <i>should</i> only be one anyway...).
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	protected static AspectJAnnotation<?> findAspectJAnnotationOnMethod(Method method) {
		for (Class<?> clazz : ASPECTJ_ANNOTATION_CLASSES) {
			AspectJAnnotation<?> foundAnnotation = findAnnotation(method, (Class<Annotation>) clazz);
			if (foundAnnotation != null) {
				return foundAnnotation;
			}
		}
		return null;
	}

	@Nullable
	private static <A extends Annotation> AspectJAnnotation<A> findAnnotation(Method method, Class<A> toLookFor) {
		A result = AnnotationUtils.findAnnotation(method, toLookFor);
		if (result != null) {
			return new AspectJAnnotation<>(result);
		}
		else {
			return null;
		}
	}


	/**
	 * Enum for AspectJ annotation types.
	 * @see AspectJAnnotation#getAnnotationType()
	 */
	protected enum AspectJAnnotationType {

		AtPointcut, AtAround, AtBefore, AtAfter, AtAfterReturning, AtAfterThrowing
	}


	/**
	 * Class modelling an AspectJ annotation, exposing its type enumeration and
	 * pointcut String.
	 * 对 AspectJ 注释进行类建模，公开其类型枚举和切入点字符串
	 * @param <A> the annotation type
	 */
	protected static class AspectJAnnotation<A extends Annotation> {

		private static final String[] EXPRESSION_ATTRIBUTES = new String[] {"pointcut", "value"};

		private static Map<Class<?>, AspectJAnnotationType> annotationTypeMap = new HashMap<>(8);

		static {
			annotationTypeMap.put(Pointcut.class, AspectJAnnotationType.AtPointcut);
			annotationTypeMap.put(Around.class, AspectJAnnotationType.AtAround);
			annotationTypeMap.put(Before.class, AspectJAnnotationType.AtBefore);
			annotationTypeMap.put(After.class, AspectJAnnotationType.AtAfter);
			annotationTypeMap.put(AfterReturning.class, AspectJAnnotationType.AtAfterReturning);
			annotationTypeMap.put(AfterThrowing.class, AspectJAnnotationType.AtAfterThrowing);
		}

		private final A annotation;

		private final AspectJAnnotationType annotationType;

		private final String pointcutExpression;

		private final String argumentNames;

		public AspectJAnnotation(A annotation) {
			this.annotation = annotation;
			this.annotationType = determineAnnotationType(annotation);
			try {
				this.pointcutExpression = resolveExpression(annotation);
				Object argNames = AnnotationUtils.getValue(annotation, "argNames");
				this.argumentNames = (argNames instanceof String ? (String) argNames : "");
			}
			catch (Exception ex) {
				throw new IllegalArgumentException(annotation + " is not a valid AspectJ annotation", ex);
			}
		}

		private AspectJAnnotationType determineAnnotationType(A annotation) {
			AspectJAnnotationType type = annotationTypeMap.get(annotation.annotationType());
			if (type != null) {
				return type;
			}
			throw new IllegalStateException("Unknown annotation type: " + annotation);
		}

		private String resolveExpression(A annotation) {
			for (String attributeName : EXPRESSION_ATTRIBUTES) {
				Object val = AnnotationUtils.getValue(annotation, attributeName);
				if (val instanceof String) {
					String str = (String) val;
					if (!str.isEmpty()) {
						return str;
					}
				}
			}
			throw new IllegalStateException("Failed to resolve expression: " + annotation);
		}

		public AspectJAnnotationType getAnnotationType() {
			return this.annotationType;
		}

		public A getAnnotation() {
			return this.annotation;
		}

		public String getPointcutExpression() {
			return this.pointcutExpression;
		}

		public String getArgumentNames() {
			return this.argumentNames;
		}

		@Override
		public String toString() {
			return this.annotation.toString();
		}
	}


	/**
	 * ParameterNameDiscoverer implementation that analyzes the arg names
	 * specified at the AspectJ annotation level.
	 */
	private static class AspectJAnnotationParameterNameDiscoverer implements ParameterNameDiscoverer {

		@Override
		@Nullable
		public String[] getParameterNames(Method method) {
			if (method.getParameterCount() == 0) {
				return new String[0];
			}
			AspectJAnnotation<?> annotation = findAspectJAnnotationOnMethod(method);
			if (annotation == null) {
				return null;
			}
			StringTokenizer nameTokens = new StringTokenizer(annotation.getArgumentNames(), ",");
			if (nameTokens.countTokens() > 0) {
				String[] names = new String[nameTokens.countTokens()];
				for (int i = 0; i < names.length; i++) {
					names[i] = nameTokens.nextToken();
				}
				return names;
			}
			else {
				return null;
			}
		}

		@Override
		@Nullable
		public String[] getParameterNames(Constructor<?> ctor) {
			throw new UnsupportedOperationException("Spring AOP cannot handle constructor advice");
		}
	}

}
