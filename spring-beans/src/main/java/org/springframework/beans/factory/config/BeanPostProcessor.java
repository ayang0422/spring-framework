/*
 * Copyright 2002-2019 the original author or authors.
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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.lang.Nullable;

/**
 * Factory hook that allows for custom modification of new bean instances &mdash;
 * for example, checking for marker interfaces or wrapping beans with proxies.
 *
 * <p>Typically, post-processors that populate beans via marker interfaces
 * or the like will implement {@link #postProcessBeforeInitialization},
 * while post-processors that wrap beans with proxies will normally
 * implement {@link #postProcessAfterInitialization}.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} can autodetect {@code BeanPostProcessor} beans
 * in its bean definitions and apply those post-processors to any beans subsequently
 * created. A plain {@code BeanFactory} allows for programmatic registration of
 * post-processors, applying them to all beans created through the bean factory.
 *
 * 允许自定义修改新 bean 实例的工厂钩子 - 例如，检查标记接口或使用代理包装 bean。
 * 通常，通过标记接口等填充 bean 的后处理器将实现postProcessBeforeInitialization ，
 * 而使用代理包装 bean 的后处理器通常将实现postProcessAfterInitialization 。
 * 登记
 * ApplicationContext可以在其 bean 定义中自动检测BeanPostProcessor bean，并将这些后处理器应用于随后创建的任何 bean。
 * 普通的BeanFactory允许以编程方式注册后处理器，将它们应用于通过 bean 工厂创建的所有 bean。
 * 订购
 * 在ApplicationContext中自动检测的BeanPostProcessor bean
 * 将根据org.springframework.core.PriorityOrdered和org.springframework.core.Ordered语义进行排序。
 * 相比之下，以编程方式注册到BeanFactory BeanPostProcessor bean 将按注册顺序应用；
 * 对于以编程方式注册的后处理器，通过实现PriorityOrdered或Ordered接口表达的任何排序语义都将被忽略。
 * 此外，@ @Order注释不会被BeanPostProcessor bean 考虑在内
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanPostProcessor} beans that are registered programmatically with a
 * {@code BeanFactory} will be applied in the order of registration; any ordering
 * semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 10.10.2003
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see ConfigurableBeanFactory#addBeanPostProcessor
 * @see BeanFactoryPostProcessor
 */
public interface BeanPostProcessor {

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>before</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 *
	 * 在任何 bean 初始化回调（如 InitializingBean 的afterPropertiesSet或自定义初始化方法）之前，
	 * 将此BeanPostProcessor应用于给定的新 bean 实例。 bean 已经被填充了属性值。
	 * 返回的 bean 实例可能是原始实例的包装器。
	 * 默认实现按原样返回给定的bean 。
	 *
	 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory的1793行
	 *
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 */
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>after</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>In case of a FactoryBean, this callback will be invoked for both the FactoryBean
	 * instance and the objects created by the FactoryBean (as of Spring 2.0). The
	 * post-processor can decide whether to apply to either the FactoryBean or created
	 * objects or both through corresponding {@code bean instanceof FactoryBean} checks.
	 * <p>This callback will also be invoked after a short-circuiting triggered by a
	 * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation} method,
	 * in contrast to all other {@code BeanPostProcessor} callbacks.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 *
	 *
	 * {@link AbstractAutowireCapableBeanFactory# * {@link AbstractAutowireCapableBeanFactory#initializeBean}}处调用
	 *
	 * 在任何 bean 初始化回调（如 InitializingBean 的afterPropertiesSet或自定义初始化方法）之后，
	 * 将此BeanPostProcessor应用于给定的新 bean 实例。 bean 已经被填充了属性值。 返回的 bean 实例可能是原始实例的包装器。
	 * 在 FactoryBean 的情况下，将为 FactoryBean 实例和 FactoryBean 创建的对象调用此回调（从 Spring 2.0 开始）。 后处理器可以通过相应的bean instanceof FactoryBean检查来决定是应用于 FactoryBean 或创建的对象还是两者。
	 * 与所有其他BeanPostProcessor回调相比，此回调也将在由InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation方法触发的短路后调用。
	 * 默认实现按原样返回给定的bean 。
	 *
	 *
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 * @see org.springframework.beans.factory.FactoryBean
	 */
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
