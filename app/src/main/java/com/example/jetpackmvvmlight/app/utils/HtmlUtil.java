/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jetpackmvvmlight.app.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * ================================================
 * 在html中引入外部css,js文件  常规拼接顺序css->html->js
 * https://github.com/HotBitmapGG/RxZhiHu/blob/https--github.com/HotBitmapGG/RxZhiHuDaily/app/src/main/java/com/hotbitmapgg/rxzhihu/utils/HtmlUtil.java
 * <p>
 * Created by JessYan on 25/04/2018 11:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">FollowSchool me</a>
 * ================================================
 */
public class HtmlUtil {

    public static Spanned fromHtml(String text) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }

    public static Spanned fromHtml(String text, int flags) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(text, flags);
        } else {
            return Html.fromHtml(text);
        }
    }
}
