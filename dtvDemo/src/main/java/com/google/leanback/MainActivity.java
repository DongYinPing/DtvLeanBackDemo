/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.leanback;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

/*
 * MainActivity class that loads {@link MainRowFragment}.
 */
public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //TODO 长按事件
    protected  long ACTION_DOWN_PRESS_LONG_TIME = 0;
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - ACTION_DOWN_PRESS_LONG_TIME < 100) {
                //小于100ms不处理长按事件
                return true;
            }
            ACTION_DOWN_PRESS_LONG_TIME = System.currentTimeMillis();
        } else {
            if (event.getAction() == KeyEvent.ACTION_UP) {
                ACTION_DOWN_PRESS_LONG_TIME = 0;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
