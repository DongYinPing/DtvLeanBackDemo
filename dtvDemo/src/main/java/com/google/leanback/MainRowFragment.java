/*
 * Copyright (C) 2017 The Android Open Source Project
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

import android.graphics.Color;

import android.os.Bundle;


import android.support.v4.content.ContextCompat;

import android.util.Log;
import android.view.Gravity;

import android.view.ViewGroup;
import android.widget.TextView;

import com.dtv.leanback.fragment.row.RowsSupportFragment;
import com.dtv.leanback.fragment.row.adapter.ArrayObjectAdapter;
import com.dtv.leanback.fragment.row.focus.FocusHighlight;
import com.dtv.leanback.fragment.row.listener.OnItemViewClickedListener;
import com.dtv.leanback.fragment.row.listener.OnItemViewSelectedListener;
import com.dtv.leanback.fragment.row.model.HeaderItem;
import com.dtv.leanback.fragment.row.model.ListRow;
import com.dtv.leanback.fragment.row.model.Row;
import com.dtv.leanback.fragment.row.presenter.ListRowPresenter;
import com.dtv.leanback.fragment.row.presenter.Presenter;
import com.dtv.leanback.fragment.row.presenter.RowPresenter;


public class MainRowFragment extends RowsSupportFragment {
    private static final String TAG = "MainRowFragment";
    private static final float scale = 1.5f;
    private static final int GRID_ITEM_WIDTH = (int) (313 / scale + 0.5f);
    private static final int GRID_ITEM_HEIGHT = (int) (176 / scale + 0.5f);

    //private static final int GRID_ITEM_WIDTH = 313;
    //private static final int GRID_ITEM_HEIGHT = 176;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        loadRows();

        setupEventListeners();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void loadRows() {

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        /*
        TODO 思想
        1: 在gridRowAdapter中传入mGridPresenter
        2: 在gridRowAdapter中传入item
        3: mGridPresenter实现绑定item

        1: 在rowsAdapter中传入ListRowPresenter
        2: 在gridRowAdapter中传入ListRow
        3：ListRow中设置HeaderItem和gridRowAdapter
        3: ListRowPresenter实现绑定ListRow
        */

        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);

        for (int i = 0; i < 30; i++) {
            gridRowAdapter.add("item: " + i);//增加行的item
        }
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter(FocusHighlight.ZOOM_FACTOR_LARGE,false));
        HeaderItem gridHeader;
        String[] headers = getResources().getStringArray(R.array.items_main_header);
        for (int i = 0; i < headers.length; i++) {
            gridHeader = new HeaderItem(0, headers[i]);
            rowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));//增加行和行的头部
        }

        //偏移10%
        setAlignmentPercent(12);

        setAdapter(rowsAdapter);
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

        }
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());

            view.setLayoutParams(new ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.fastlane_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}
