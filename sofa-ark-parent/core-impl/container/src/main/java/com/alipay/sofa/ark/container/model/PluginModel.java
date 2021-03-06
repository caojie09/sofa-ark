/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.ark.container.model;

import com.alipay.sofa.ark.common.util.ClassLoaderUtils;
import com.alipay.sofa.ark.common.util.ParseUtils;
import com.alipay.sofa.ark.common.util.StringUtils;
import com.alipay.sofa.ark.exception.ArkRuntimeException;
import com.alipay.sofa.ark.spi.constant.Constants;
import com.alipay.sofa.ark.spi.model.Plugin;
import com.alipay.sofa.ark.spi.model.PluginContext;
import com.alipay.sofa.ark.spi.service.PluginActivator;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * ARk Plugin Standard Model
 *
 * @author qilong.zql
 * @since 0.1.0
 */
public class PluginModel implements Plugin {

    private String          pluginName;

    private String          groupId;

    private String          artifactId;

    private String          version;

    private int             priority            = DEFAULT_PRECEDENCE;

    private Set<String>     exportPackages;

    private Set<String>     exportPackageNodes  = new HashSet<>();

    private Set<String>     exportPackageStems  = new HashSet<>();

    private Set<String>     exportClasses;

    private Set<String>     importPackages;

    private Set<String>     importPackageNodes  = new HashSet<>();

    private Set<String>     importPackageStems  = new HashSet<>();

    private Set<String>     importClasses;

    private Set<String>     importResources     = new HashSet<>();

    private Set<String>     importResourceStems = new HashSet<>();

    private Set<String>     exportResources     = new HashSet<>();

    private Set<String>     exportResourceStems = new HashSet<>();

    private String          activator;

    private URL[]           urls;

    private URL             pluginUrl;

    private ClassLoader     pluginClassLoader;

    private PluginContext   pluginContext;

    private PluginActivator pluginActivator;

    public PluginModel setPluginName(String pluginName) {
        this.pluginName = pluginName;
        return this;
    }

    public PluginModel setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public PluginModel setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    public PluginModel setVersion(String version) {
        this.version = version;
        return this;
    }

    public PluginModel setPriority(String priority) {
        this.priority = (priority == null ? DEFAULT_PRECEDENCE : Integer.valueOf(priority));
        return this;
    }

    public PluginModel setPluginActivator(String activator) {
        this.activator = activator;
        return this;
    }

    public PluginModel setClassPath(URL[] urls) {
        this.urls = urls;
        return this;
    }

    public PluginModel setExportPackages(String exportPackages) {
        this.exportPackages = StringUtils.strToSet(exportPackages, Constants.MANIFEST_VALUE_SPLIT);
        ParseUtils.parsePackageNodeAndStem(this.exportPackages, this.exportPackageStems,
            this.exportPackageNodes);
        return this;
    }

    public PluginModel setExportClasses(String exportClasses) {
        this.exportClasses = StringUtils.strToSet(exportClasses, Constants.MANIFEST_VALUE_SPLIT);
        return this;
    }

    public PluginModel setImportPackages(String importPackages) {
        this.importPackages = StringUtils.strToSet(importPackages, Constants.MANIFEST_VALUE_SPLIT);
        ParseUtils.parsePackageNodeAndStem(this.importPackages, this.importPackageStems,
            this.importPackageNodes);
        return this;
    }

    public PluginModel setImportClasses(String importClasses) {
        this.importClasses = StringUtils.strToSet(importClasses, Constants.MANIFEST_VALUE_SPLIT);
        return this;
    }

    public PluginModel setImportResources(String importResources) {
        ParseUtils.parseResourceAndStem(
            StringUtils.strToSet(importResources, Constants.MANIFEST_VALUE_SPLIT),
            this.importResourceStems, this.importResources);
        return this;
    }

    public PluginModel setExportResources(String exportResources) {
        ParseUtils.parseResourceAndStem(
            StringUtils.strToSet(exportResources, Constants.MANIFEST_VALUE_SPLIT),
            this.exportResourceStems, this.exportResources);
        return this;
    }

    public PluginModel setPluginClassLoader(ClassLoader classLoader) {
        this.pluginClassLoader = classLoader;
        return this;
    }

    public PluginModel setPluginContext(PluginContext context) {
        this.pluginContext = context;
        return this;
    }

    public PluginModel setPluginUrl(URL pluginUrl) {
        this.pluginUrl = pluginUrl;
        return this;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public String getPluginName() {
        return this.pluginName;
    }

    @Override
    public String getGroupId() {
        return this.groupId;
    }

    @Override
    public String getArtifactId() {
        return this.artifactId;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String getPluginActivator() {
        return this.activator;
    }

    @Override
    public URL[] getClassPath() {
        return this.urls;
    }

    @Override
    public ClassLoader getPluginClassLoader() {
        return this.pluginClassLoader;
    }

    @Override
    public PluginContext getPluginContext() {
        return this.pluginContext;
    }

    @Override
    public Set<String> getExportPackages() {
        return this.exportPackages;
    }

    @Override
    public Set<String> getExportPackageNodes() {
        return exportPackageNodes;
    }

    @Override
    public Set<String> getExportPackageStems() {
        return exportPackageStems;
    }

    @Override
    public Set<String> getExportClasses() {
        return this.exportClasses;
    }

    @Override
    public Set<String> getImportPackages() {
        return this.importPackages;
    }

    @Override
    public Set<String> getImportPackageNodes() {
        return this.importPackageNodes;
    }

    @Override
    public Set<String> getImportPackageStems() {
        return this.importPackageStems;
    }

    @Override
    public Set<String> getImportClasses() {
        return this.importClasses;
    }

    @Override
    public Set<String> getImportResources() {
        return importResources;
    }

    @Override
    public Set<String> getImportResourceStems() {
        return importResourceStems;
    }

    @Override
    public Set<String> getExportResources() {
        return exportResources;
    }

    @Override
    public Set<String> getExportResourceStems() {
        return exportResourceStems;
    }

    @Override
    public URL getPluginURL() {
        return pluginUrl;
    }

    @Override
    public void start() throws ArkRuntimeException {
        if (activator == null || activator.isEmpty()) {
            return;
        }

        ClassLoader oldClassLoader = ClassLoaderUtils
            .pushContextClassLoader(this.pluginClassLoader);
        try {
            pluginActivator = (PluginActivator) pluginClassLoader.loadClass(activator)
                .newInstance();
            pluginActivator.start(pluginContext);
        } catch (Throwable ex) {
            throw new ArkRuntimeException(ex.getMessage(), ex);
        } finally {
            ClassLoaderUtils.popContextClassLoader(oldClassLoader);
        }
    }

    @Override
    public void stop() throws ArkRuntimeException {
        if (pluginActivator != null) {
            pluginActivator.stop(pluginContext);
        }
    }

    @Override
    public String toString() {
        return "Ark Plugin: " + pluginName;
    }

}