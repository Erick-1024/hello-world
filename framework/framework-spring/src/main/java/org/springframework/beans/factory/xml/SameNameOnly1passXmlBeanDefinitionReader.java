package org.springframework.beans.factory.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.Assert;
import org.xml.sax.InputSource;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class SameNameOnly1passXmlBeanDefinitionReader extends XmlBeanDefinitionReader {

	private Logger logger = LoggerFactory.getLogger(XmlBeanDefinitionReader.class);

	Set<String> pathnameSet = Sets.newConcurrentHashSet();
	Map<String, Object> pathObjMap = Maps.newConcurrentMap();

	public SameNameOnly1passXmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded = new NamedThreadLocal<Set<EncodedResource>>(
			"XML bean definition resources currently being loaded");

	private String getResourcePath(UrlResource url) {
		String urlStr = url.toString();

		int idx = urlStr.indexOf("!");
		if (idx > 0) {
			String targetStr = StringUtils.substring(urlStr, idx + 1);
            if(StringUtils.startsWith(targetStr,"/")) return StringUtils.substring(targetStr,1);
			return targetStr;
		}
		String pattern = "WEB-INF/classes/";
		idx = urlStr.indexOf(pattern);
		if (idx > 0) {
			String targetStr = StringUtils.substring(urlStr, idx + pattern.length());
			return targetStr;
		}

		return urlStr;

	}

	@Override
	public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
		Assert.notNull(encodedResource, "EncodedResource must not be null");

		if (encodedResource.getResource() instanceof UrlResource) {
			UrlResource url = (UrlResource) encodedResource.getResource();
			String path = getResourcePath(url);
			if (pathnameSet.contains(path)) {
				logger.info("URL:{}", url.toString());
				logger.info(getResourcePath(url) + " has been processed , ignored");
				logger.info("Effective path:{}", pathObjMap.get(path).toString());
				return 0;
			} else {
				pathnameSet.add(path);
				pathObjMap.put(path, url);
			}
		}

		if (encodedResource.getResource() instanceof ClassPathResource) {
			ClassPathResource cp = (ClassPathResource) encodedResource.getResource();
			String path = cp.getPath();
			if (pathnameSet.contains(path)) {
				logger.info("CP:{}", cp.toString());
				logger.info(cp.getPath() + " has been processed , ignored");
				logger.info("Effective path:{}", pathObjMap.get(path).toString());
				return 0;
			} else {
				pathnameSet.add(path);
				pathObjMap.put(path, cp);
			}
		}

		//////

		if (logger.isInfoEnabled()) {
			logger.info("Loading XML bean definitions from " + encodedResource.getResource());
		}

		Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();
		if (currentResources == null) {
			currentResources = new HashSet<EncodedResource>(4);
			this.resourcesCurrentlyBeingLoaded.set(currentResources);
		}
		if (!currentResources.add(encodedResource)) {
			throw new BeanDefinitionStoreException("Detected cyclic loading of " + encodedResource + " - check your import definitions!");
		}
		try {
			InputStream inputStream = encodedResource.getResource().getInputStream();
			try {
				InputSource inputSource = new InputSource(inputStream);
				if (encodedResource.getEncoding() != null) {
					inputSource.setEncoding(encodedResource.getEncoding());
				}
				return doLoadBeanDefinitions(inputSource, encodedResource.getResource());
			} finally {
				inputStream.close();
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException("IOException parsing XML document from " + encodedResource.getResource(), ex);
		} finally {
			currentResources.remove(encodedResource);
			if (currentResources.isEmpty()) {
				this.resourcesCurrentlyBeingLoaded.remove();
			}
		}
	}
	//
	//	/**
	//	 * Load bean definitions from the specified resource location.
	//	 * <p>The location can also be a location pattern, provided that the
	//	 * ResourceLoader of this bean definition reader is a ResourcePatternResolver.
	//	 * @param location the resource location, to be loaded with the ResourceLoader
	//	 * (or ResourcePatternResolver) of this bean definition reader
	//	 * @param actualResources a Set to be filled with the actual Resource objects
	//	 * that have been resolved during the loading process. May be {@code null}
	//	 * to indicate that the caller is not interested in those Resource objects.
	//	 * @return the number of bean definitions found
	//	 * @throws BeanDefinitionStoreException in case of loading or parsing errors
	//	 * @see #getResourceLoader()
	//	 * @see #loadBeanDefinitions(org.springframework.core.io.Resource)
	//	 * @see #loadBeanDefinitions(org.springframework.core.io.Resource[])
	//	 */
	//	@Override
	//	public int loadBeanDefinitions(String location, Set<Resource> actualResources) throws BeanDefinitionStoreException {
	//		ResourceLoader resourceLoader = getResourceLoader();
	//		if (resourceLoader == null) {
	//			throw new BeanDefinitionStoreException("Cannot import bean definitions from location [" + location + "]: no ResourceLoader available");
	//		}
	//
	//		if (resourceLoader instanceof ResourcePatternResolver) {
	//			// Resource pattern matching available.
	//			try {
	//				Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
	//				int loadCount = loadBeanDefinitions(resources);
	//				if (actualResources != null) {
	//					for (Resource resource : resources) {
	//						actualResources.add(resource);
	//					}
	//				}
	//				if (logger.isDebugEnabled()) {
	//					logger.debug("Loaded " + loadCount + " bean definitions from location pattern [" + location + "]");
	//				}
	//				return loadCount;
	//			} catch (IOException ex) {
	//				throw new BeanDefinitionStoreException("Could not resolve bean definition resource pattern [" + location + "]", ex);
	//			}
	//		} else {
	//			// Can only load single resources by absolute URL.
	//			Resource resource = resourceLoader.getResource(location);
	//			int loadCount = loadBeanDefinitions(resource);
	//			if (actualResources != null) {
	//				actualResources.add(resource);
	//			}
	//			if (logger.isDebugEnabled()) {
	//				logger.debug("Loaded " + loadCount + " bean definitions from location [" + location + "]");
	//			}
	//			return loadCount;
	//		}
	//	}

}
