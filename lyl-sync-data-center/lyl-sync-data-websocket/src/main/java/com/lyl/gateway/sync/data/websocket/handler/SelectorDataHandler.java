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

package com.lyl.gateway.sync.data.websocket.handler;

import com.lyl.gateway.common.dto.SelectorData;
import com.lyl.gateway.common.utils.GsonUtils;
import com.lyl.gateway.sync.data.api.PluginDataSubscriber;
import lombok.RequiredArgsConstructor;

import java.util.List;


/**
 *@Author lyl
 *@Desc 
 *@Date 2021/5/31 16:39
 **/
@RequiredArgsConstructor
public class SelectorDataHandler extends AbstractDataHandler<SelectorData> {

    private final PluginDataSubscriber pluginDataSubscriber;

    @Override
    public List<SelectorData> convert(final String json) {
        return GsonUtils.getInstance().fromList(json, SelectorData.class);
    }

    @Override
    protected void doRefresh(final List<SelectorData> dataList) {
        pluginDataSubscriber.refreshSelectorDataSelf(dataList);
        dataList.forEach(pluginDataSubscriber::onSelectorSubscribe);
    }

    @Override
    protected void doUpdate(final List<SelectorData> dataList) {
        dataList.forEach(pluginDataSubscriber::onSelectorSubscribe);
    }

    @Override
    protected void doDelete(final List<SelectorData> dataList) {
        dataList.forEach(pluginDataSubscriber::unSelectorSubscribe);
    }
}
