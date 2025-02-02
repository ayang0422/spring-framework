/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * 访问spring bean 的根接口
 * The root interface for accessing a Spring bean container.
 *
 * <p>This is the basic client view of a bean container;
 * further interfaces such as {@link ListableBeanFactory} and
 * {@link org.springframework.beans.factory.config.ConfigurableBeanFactory}
 * are available for specific purposes.
 *
 * <p>This interface is implemented by objects that hold a number of bean definitions,
 * each uniquely identified by a String name. Depending on the bean definition,
 * the factory will return either an independent instance of a contained object
 * (the Prototype design pattern), or a single shared instance (a superior
 * alternative to the Singleton design pattern, in which the instance is a
 * singleton in the scope of the factory). Which type of instance will be returned
 * depends on the bean factory configuration: the API is the same. Since Spring
 * 2.0, further scopes are available depending on the concrete application
 * context (e.g. "request" and "session" scopes in a web environment).
 *
 * <p>The point of this approach is that the BeanFactory is a central registry
 * of application components, and centralizes configuration of application
 * components (no more do individual objects need to read properties files,
 * for example). See chapters 4 and 11 of "Expert One-on-One J2EE Design and
 * Development" for a discussion of the benefits of this approach.
 *
 * <p>Note that it is generally better to rely on Dependency Injection
 * ("push" configuration) to configure application objects through setters
 * or constructors, rather than use any form of "pull" configuration like a
 * BeanFactory lookup. Spring's Dependency Injection functionality is
 * implemented using this BeanFactory interface and its subinterfaces.
 *
 * <p>Normally a BeanFactory will load bean definitions stored in a configuration
 * source (such as an XML document), and use the {@code org.springframework.beans}
 * package to configure the beans. However, an implementation could simply return
 * Java objects it creates as necessary directly in Java code. There are no
 * constraints on how the definitions could be stored: LDAP, RDBMS, XML,
 * properties file, etc. Implementations are encouraged to support references
 * amongst beans (Dependency Injection).
 *
 * <p>这个接口是由持有许多 bean 定义的对象实现的，每个定义由一个字符串名称唯一标识。
 * 根据 bean 定义，工厂将返回包含对象的独立实例（原型设计模式），
 * 或单个共享实例（单例设计模式的高级替代方案，其中实例是范围内的单例）工厂）。
 * 返回哪种类型的实例取决于 bean factory 配置：API 是相同的。
 * 从 Spring 2.0 开始，根据具体的应用程序上下文（例如 Web 环境中的“请求”和“会话”范围），可以使用更多范围。
 *
 * <p>这种方法的重点是 BeanFactory 是应用程序组件的中央注册表，并且集中了应用程序组件的配置（例如，单个对象不再需要读取属性文件）。
 * 请参阅“专家一对一 J2EE 设计和开发”的第 4 章和第 11 章，以了解这种方法的好处。
 *
 * <p>请注意，通常最好依靠依赖注入（“推”配置）通过 setter 或构造函数来配置应用程序对象，而不是使用任何形式的“拉”配置，
 * 例如 BeanFactory 查找。
 * Spring 的依赖注入功能是使用这个 BeanFactory 接口及其子接口实现的。
 *
 * <p>通常 BeanFactory 会加载存储在配置源（例如 XML 文档）中的 bean 定义，
 * 并使用 {@code org.springframework.beans} 包来配置 bean。
 * 但是，实现可以简单地直接在 Java 代码中返回它根据需要创建的 Java 对象。
 * 定义的存储方式没有限制：LDAP、RDBMS、XML、属性文件等。
 * 鼓励实现支持 bean 之间的引用（依赖注入）。
 *
 * <p>In contrast to the methods in {@link ListableBeanFactory}, all of the
 * operations in this interface will also check parent factories if this is a
 * {@link HierarchicalBeanFactory}. If a bean is not found in this factory instance,
 * the immediate parent factory will be asked. Beans in this factory instance
 * are supposed to override beans of the same name in any parent factory.
 *
 * 与 {@link ListableBeanFactory} 中的方法相反，
 * 此接口中的所有操作还将检查父工厂是否为 {@link HierarchicalBeanFactory}。
 * 如果在此工厂实例中未找到 bean，则会询问直接父工厂。
 * 此工厂实例中的 Bean 应该覆盖任何父工厂中的同名 Bean。
 *
 * <p>Bean factory implementations should support the standard bean lifecycle interfaces
 * as far as possible. The full set of initialization methods and their standard order is:
 *
 * Bean 工厂实现应尽可能支持标准的 Bean 生命周期接口。全套初始化方法及其标准顺序是：
 * Aware指的是bean的
 * <ol>
 * 		<li>BeanNameAware's {@code setBeanName}
 * 		<li>BeanClassLoaderAware's {@code setBeanClassLoader}
 * 		<li>BeanFactoryAware's {@code setBeanFactory}
 * 		<li>EnvironmentAware's {@code setEnvironment}
 * 		<li>EmbeddedValueResolverAware's {@code setEmbeddedValueResolver}
 * 		<li>ResourceLoaderAware's {@code setResourceLoader}
 * 		(only applicable when running in an application context)
 * 		<li>ApplicationEventPublisherAware's {@code setApplicationEventPublisher}
 * 		事件发布
 * 		(only applicable when running in an application context)
 * 		<li>MessageSourceAware's {@code setMessageSource}
 * 		信息源(国际化)
 * 		(only applicable when running in an application context)
 * 		<li>ApplicationContextAware's {@code setApplicationContext}
 * 		应用上下文
 * 		(only applicable when running in an application context)
 * 		<li>ServletContextAware's {@code setServletContext}
 * 		servlet上下文
 * 		(only applicable when running in a web application context)
 * 		<li>{@code postProcessBeforeInitialization} methods of BeanPostProcessors
 * 		beanPostProcessor
 * 		<li>InitializingBean's {@code afterPropertiesSet}
 * 		初始化bean
 * 		<li>a custom init-method definition
 * 		执行init-method
 * 		<li>{@code postProcessAfterInitialization} methods of BeanPostProcessors
 * </ol>
 *
 * <p>On shutdown of a bean factory, the following lifecycle methods apply
 * 		在关闭bean工厂,生命周期方法如下:
 * <ol>
	 * <li>{@code postProcessBeforeDestruction} methods of DestructionAwareBeanPostProcessors
	 * <li>DisposableBean's {@code destroy}
	 * <li>a custom destroy-method definition
 * </ol>
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Chris Beams
 * @since 13 April 2001
 * @see BeanNameAware#setBeanName
 * @see BeanClassLoaderAware#setBeanClassLoader
 * @see BeanFactoryAware#setBeanFactory
 * @see org.springframework.context.ResourceLoaderAware#setResourceLoader
 * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher
 * @see org.springframework.context.MessageSourceAware#setMessageSource
 * @see org.springframework.context.ApplicationContextAware#setApplicationContext
 * @see org.springframework.web.context.ServletContextAware#setServletContext
 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization
 * @see InitializingBean#afterPropertiesSet
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getInitMethodName
 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization
 * @see DisposableBean#destroy
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getDestroyMethodName
 */
public interface BeanFactory {

	/**
	 * Used to dereference a {@link FactoryBean} instance and distinguish it from
	 * beans <i>created</i> by the FactoryBean. For example, if the bean named
	 * {@code myJndiObject} is a FactoryBean, getting {@code &myJndiObject}
	 * will return the factory, not the instance returned by the factory.
	 *
	 * 用于取消引用FactoryBean实例并将其与 FactoryBean创建的 bean 区分开来。
	 * 例如，如果名为myJndiObject的 bean 是 FactoryBean，则获取&myJndiObject将返回工厂，而不是工厂返回的实例。
	 *   spring.spring-beans.main
	 */
	String FACTORY_BEAN_PREFIX = "&";


	/**
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>This method allows a Spring BeanFactory to be used as a replacement for the
	 * Singleton or Prototype design pattern. Callers may retain references to
	 * returned objects in the case of Singleton beans.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 返回指定 bean 的一个实例，它可以是共享的或独立的。
	 * 此方法允许使用 Spring BeanFactory 作为单例或原型设计模式的替代品。 在单例 bean 的情况下，调用者可以保留对返回对象的引用。
	 * 将别名转换回相应的规范 bean 名称。
	 * 如果在这个工厂实例中找不到 bean，将询问父工厂。
	 *
	 * @param name the name of the bean to retrieve
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no bean with the specified name
	 * @throws BeansException if the bean could not be obtained
	 */
	Object getBean(String name) throws BeansException;

	/**
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>Behaves the same as {@link #getBean(String)}, but provides a measure of type
	 * safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the
	 * required type. This means that ClassCastException can't be thrown on casting
	 * the result correctly, as can happen with {@link #getBean(String)}.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 返回指定 bean 的一个实例，它可以是共享的或独立的。
	 * 行为与getBean(String)相同，但如果 bean 不是所需类型，则通过抛出 BeanNotOfRequiredTypeException 来提供类型安全措施。
	 * 这意味着在正确投射结果时不能抛出 ClassCastException，就像getBean(String)可能发生的那样。
	 * 将别名转换回相应的规范 bean 名称。
	 * 如果在这个工厂实例中找不到 bean，将询问父工厂。
	 *
	 * @param name the name of the bean to retrieve
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no such bean definition
	 * @throws BeanNotOfRequiredTypeException if the bean is not of the required type
	 * @throws BeansException if the bean could not be created
	 */
	<T> T getBean(String name, Class<T> requiredType) throws BeansException;

	/**
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>Allows for specifying explicit constructor arguments / factory method arguments,
	 * overriding the specified default arguments (if any) in the bean definition.
	 *
	 * 返回指定 bean 的一个实例，它可以是共享的或独立的。
	 * 允许指定显式构造函数参数/工厂方法参数，覆盖 bean 定义中指定的默认参数（如果有）。
	 *
	 * @param name the name of the bean to retrieve
	 * @param args arguments to use when creating a bean instance using explicit arguments
	 * (only applied when creating a new instance as opposed to retrieving an existing one)
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no such bean definition
	 * @throws BeanDefinitionStoreException if arguments have been given but
	 * the affected bean isn't a prototype
	 * @throws BeansException if the bean could not be created
	 * @since 2.5
	 */
	Object getBean(String name, Object... args) throws BeansException;

	/**
	 * Return the bean instance that uniquely matches the given object type, if any.
	 * <p>This method goes into {@link ListableBeanFactory} by-type lookup territory
	 * but may also be translated into a conventional by-name lookup based on the name
	 * of the given type. For more extensive retrieval operations across sets of beans,
	 * use {@link ListableBeanFactory} and/or {@link BeanFactoryUtils}.
	 *
	 * 返回与给定对象类型唯一匹配的 bean 实例（如果有）。
	 * 此方法进入ListableBeanFactory按类型查找领域，但也可以转换为基于给定类型名称的传统按名称查找。
	 * 对于跨 bean 集的更广泛的检索操作，请使用ListableBeanFactory和/或BeanFactoryUtils
	 *
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @return an instance of the single bean matching the required type
	 * @throws NoSuchBeanDefinitionException if no bean of the given type was found
	 * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
	 * @throws BeansException if the bean could not be created
	 * @since 3.0
	 * @see ListableBeanFactory
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;

	/**
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>Allows for specifying explicit constructor arguments / factory method arguments,
	 * overriding the specified default arguments (if any) in the bean definition.
	 * <p>This method goes into {@link ListableBeanFactory} by-type lookup territory
	 * but may also be translated into a conventional by-name lookup based on the name
	 * of the given type. For more extensive retrieval operations across sets of beans,
	 * use {@link ListableBeanFactory} and/or {@link BeanFactoryUtils}.
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @param args arguments to use when creating a bean instance using explicit arguments
	 * (only applied when creating a new instance as opposed to retrieving an existing one)
	 *
	 * 返回指定 bean 的一个实例，该实例可以是共享的，也可以是独立的。
	 * <p>允许指定显式构造函数参数工厂方法参数，覆盖 bean 定义中指定的默认参数（如果有）。
	 * <p>此方法进入 {@link ListableBeanFactory} 按类型查找领域，但也可以根据给定类型的名称转换为传统的按名称查找。
	 * 对于跨 bean 集的更广泛的检索操作，请使用 {@link ListableBeanFactory} 和或 {@link BeanFactoryUtils}。
	 *
	 *
	 * @param requiredType 类型 bean 必须匹配；可以是使用显式参数创建 bean 实例时使用的接口或超类
	 * @param args 参数（仅在创建新实例时应用，而不是检索现有实例）
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no such bean definition
	 * @throws BeanDefinitionStoreException if arguments have been given but
	 * the affected bean isn't a prototype
	 * @throws BeansException if the bean could not be created
	 * @since 4.1
	 */
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;

	/**
	 * Return a provider for the specified bean, allowing for lazy on-demand retrieval
	 * of instances, including availability and uniqueness options.
	 *
	 * 返回指定 bean 的提供者，允许延迟按需检索实例，包括可用性和唯一性选项。
	 *
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @return a corresponding provider handle
	 * @since 5.1
	 * @see #getBeanProvider(ResolvableType)
	 */
	<T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);

	/**
	 * Return a provider for the specified bean, allowing for lazy on-demand retrieval
	 * of instances, including availability and uniqueness options.
	 *
	 * 返回指定 bean 的提供者，允许延迟按需检索实例，包括可用性和唯一性选项。
	 *
	 * @param requiredType type the bean must match; can be a generic type declaration.
	 * Note that collection types are not supported here, in contrast to reflective
	 * injection points. For programmatically retrieving a list of beans matching a
	 * specific type, specify the actual bean type as an argument here and subsequently
	 * use {@link ObjectProvider#orderedStream()} or its lazy streaming/iteration options.
	 * @return a corresponding provider handle
	 * @since 5.1
	 * @see ObjectProvider#iterator()
	 * @see ObjectProvider#stream()
	 * @see ObjectProvider#orderedStream()
	 */
	<T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType);

	/**
	 * Does this bean factory contain a bean definition or externally registered singleton
	 * instance with the given name?
	 *
	 * 这个 bean 工厂是否包含 bean 定义或外部注册的单例
	 * 具有给定名称的实例？
	 *
	 * <p>If the given name is an alias, it will be translated back to the corresponding
	 * canonical bean name.
	 * <p>If this factory is hierarchical, will ask any parent factory if the bean cannot
	 * be found in this factory instance.
	 * <p>If a bean definition or singleton instance matching the given name is found,
	 * this method will return {@code true} whether the named bean definition is concrete
	 * or abstract, lazy or eager, in scope or not. Therefore, note that a {@code true}
	 * return value from this method does not necessarily indicate that {@link #getBean}
	 * will be able to obtain an instance for the same name.
	 * @param name the name of the bean to query
	 * @return whether a bean with the given name is present
	 */
	boolean containsBean(String name);

	/**
	 * Is this bean a shared singleton? That is, will {@link #getBean} always
	 * return the same instance?
	 *
	 * 这个 bean 是共享单例吗？也就是说，{@link getBean} 是否总是返回相同的实例？
	 *
	 * <p>Note: This method returning {@code false} does not clearly indicate
	 * independent instances. It indicates non-singleton instances, which may correspond
	 * to a scoped bean as well. Use the {@link #isPrototype} operation to explicitly
	 * check for independent instances.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 * @param name the name of the bean to query
	 * @return whether this bean corresponds to a singleton instance
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @see #getBean
	 * @see #isPrototype
	 */
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

	/**
	 * Is this bean a prototype? That is, will {@link #getBean} always return
	 * independent instances?
	 *
	 * 这个bean是原型吗？也就是说，{@link getBean} 会一直返回独立的实例吗？
	 *
	 * <p>Note: This method returning {@code false} does not clearly indicate
	 * a singleton object. It indicates non-independent instances, which may correspond
	 * to a scoped bean as well. Use the {@link #isSingleton} operation to explicitly
	 * check for a shared singleton instance.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 * @param name the name of the bean to query
	 * @return whether this bean will always deliver independent instances
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 2.0.3
	 * @see #getBean
	 * @see #isSingleton
	 */
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	/**
	 * Check whether the bean with the given name matches the specified type.
	 * More specifically, check whether a {@link #getBean} call for the given name
	 * would return an object that is assignable to the specified target type.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 检查具有给定名称的 bean 是否与指定的类型匹配。
	 * 更具体地说，检查对给定名称的 {@link getBean} 调用是否会返回可分配给指定目标类型的对象。
	 * <p>将别名翻译回相应的规范 bean 名称。 <p>如果在这个工厂实例中找不到bean，会询问父工厂。
	 *
	 * @param name the name of the bean to query
	 * @param typeToMatch the type to match against (as a {@code ResolvableType})
	 * @return {@code true} if the bean type matches,
	 * {@code false} if it doesn't match or cannot be determined yet
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 4.2
	 * @see #getBean
	 * @see #getType
	 */
	boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException;

	/**
	 * Check whether the bean with the given name matches the specified type.
	 * More specifically, check whether a {@link #getBean} call for the given name
	 * would return an object that is assignable to the specified target type.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 检查具有给定名称的 bean 是否与指定的类型匹配。
	 * 更具体地说，检查是否有 {@link #getBean} 调用给定名称
	 * 将返回一个可分配给指定目标类型的对象。
	 * <p>将别名翻译回相应的规范 bean 名称。
	 * <p>如果在这个工厂实例中找不到bean，会询问父工厂。
	 *
	 * @param name the name of the bean to query
	 * @param typeToMatch the type to match against (as a {@code Class})
	 * @return {@code true} if the bean type matches,
	 * {@code false} if it doesn't match or cannot be determined yet
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 2.0.1
	 * @see #getBean
	 * @see #getType
	 */
	boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException;

	/**
	 * Determine the type of the bean with the given name. More specifically,
	 * determine the type of object that {@link #getBean} would return for the given name.
	 * <p>For a {@link FactoryBean}, return the type of object that the FactoryBean creates,
	 * as exposed by {@link FactoryBean#getObjectType()}. This may lead to the initialization
	 * of a previously uninitialized {@code FactoryBean} (see {@link #getType(String, boolean)}).
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 确定具有给定名称的 bean 的类型。进一步来说，
	 * 确定 {@link #getBean} 将为给定名称返回的对象类型。
	 * <p>对于{@link FactoryBean}，返回FactoryBean创建的对象类型，
	 * 由 {@link FactoryBean#getObjectType()} 公开。这可能会导致初始化
	 * 之前未初始化的 {@code FactoryBean}（参见 {@link #getType(String, boolean)}）。
	 * <p>将别名翻译回相应的规范 bean 名称。
	 * <p>如果在这个工厂实例中找不到bean，会询问父工厂。
	 *
	 * @param name the name of the bean to query
	 * @return the type of the bean, or {@code null} if not determinable
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 1.1.2
	 * @see #getBean
	 * @see #isTypeMatch
	 */
	@Nullable
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

	/**
	 * Determine the type of the bean with the given name. More specifically,
	 * determine the type of object that {@link #getBean} would return for the given name.
	 * <p>For a {@link FactoryBean}, return the type of object that the FactoryBean creates,
	 * as exposed by {@link FactoryBean#getObjectType()}. Depending on the
	 * {@code allowFactoryBeanInit} flag, this may lead to the initialization of a previously
	 * uninitialized {@code FactoryBean} if no early type information is available.
	 * <p>Translates aliases back to the corresponding canonical bean name.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 确定具有给定名称的 bean 的类型。更具体地说，确定 {@link getBean} 将为给定名称返回的对象类型。
	 * <p>对于 {@link FactoryBean}，返回由 {@link FactoryBeangetObjectType()} 公开的 FactoryBean 创建的对象类型。
	 * 根据 {@code allowFactoryBeanInit} 标志，如果没有可用的早期类型信息，这可能会导致先前未初始化的 {@code FactoryBean} 的初始化。
	 * <p>将别名翻译回相应的规范 bean 名称。
	 * <p>如果在这个工厂实例中找不到bean，会询问父工厂
	 *
	 * @param name the name of the bean to query
	 * @param allowFactoryBeanInit whether a {@code FactoryBean} may get initialized
	 * just for the purpose of determining its object type
	 * @return the type of the bean, or {@code null} if not determinable
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 5.2
	 * @see #getBean
	 * @see #isTypeMatch
	 */
	@Nullable
	Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException;

	/**
	 * Return the aliases for the given bean name, if any.
	 * <p>All of those aliases point to the same bean when used in a {@link #getBean} call.
	 * <p>If the given name is an alias, the corresponding original bean name
	 * and other aliases (if any) will be returned, with the original bean name
	 * being the first element in the array.
	 * <p>Will ask the parent factory if the bean cannot be found in this factory instance.
	 *
	 * 返回给定 bean 名称的别名（如果有）。
	 * <p>在 {@link getBean} 调用中使用时，所有这些别名都指向同一个 bean。
	 * <p>如果给定的名称是别名，则返回对应的原始 bean 名称和其他别名（如果有），原始 bean 名称是数组中的第一个元素。
	 * <p>如果在这个工厂实例中找不到bean，会询问父工厂。
	 *
	 * @param name the bean name to check for aliases
	 * @return the aliases, or an empty array if none
	 * @see #getBean
	 */
	String[] getAliases(String name);

}
