package com.yuntianhe.xdecoration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuntianhe.xdecoration.dividers.TestDividerFactory;
import com.yuntianhe.xdecoration.library.XDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int VERTICAL = 1;
    int HORIZONTAL = 2;

    int direction = HORIZONTAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int spanCount = 3;

        RecyclerView recyclerViewV = findViewById(R.id.recycler_view_v);
        RecyclerView recyclerViewH = findViewById(R.id.recycler_view_h);

        RecyclerView recyclerView;
        if (direction == VERTICAL) {
            recyclerView = recyclerViewV;
        } else {
            recyclerView = recyclerViewH;
        }
        recyclerView.setVisibility(View.VISIBLE);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, direction == VERTICAL ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL, false);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, direction == VERTICAL ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL, false);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, direction == VERTICAL ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        List<Item> data = new ArrayList<>();

//        data.add(new BigHeader());
//
//        for (int i = 0; i < 2; i++) {
//            data.add(new Header());
//            for (int j = 0; j < 3; j++) {
//                data.add(new Product());
//            }
//        }

        Random r = new Random();
        for (int i = 0; i < 1; i++) {
//            data.add(new RecHeader(r.nextBoolean()));
            for (int j = 0; j < 20; j++) {
                Rec rec = new Rec(r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean());
                data.add(rec);
            }
        }

        final MyAdapter adapter = new MyAdapter(this, data);
        recyclerView.setAdapter(adapter);

        XDecoration itemDecoration = new XDecoration(this);
        itemDecoration.setDividerFactory(new TestDividerFactory());

        recyclerView.addItemDecoration(itemDecoration);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {
        private Context context;
        private List<Item> mData;
        private List<Item> mRealRecList = new ArrayList<>();

        public MyAdapter(Context context, List<Item> data) {
            this.context = context;
            this.mData = data;
        }

        @NonNull
        @Override
        public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == Item.TYPE_BIG_HEADER) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_big_header, parent, false);
                return new HeaderVH(view);
            } else if (viewType == Item.TYPE_HEADER) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
                return new HeaderVH(view);
            } else if (viewType == Item.TYPE_PRODUCT) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
                return new ProductVH(view);
            } else if (viewType == Item.TYPE_REC_HEADER) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_rec_header_h, parent, false);
                return new RecHeaderVH(view);
            } else {
                if (direction == VERTICAL) {
                    View view = LayoutInflater.from(context).inflate(R.layout.item_rec, parent, false);
                    return new RecVh(view);
                } else {
                    View view = LayoutInflater.from(context).inflate(R.layout.item_rec_h, parent, false);
                    return new RecVh(view);
                }
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyVH holder, int position) {
            if (holder instanceof RecHeaderVH) {
                RecHeaderVH vh = (RecHeaderVH) holder;
                vh.bind();
            } else if (holder instanceof RecVh) {
                RecVh vh = (RecVh) holder;
                vh.bind();
            }
        }

        @Override
        public int getItemViewType(int position) {
            Item item = mData.get(position);
            return item.getItemViewType();
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }

        public Item getItem(int position) {
            if (mData != null && !mData.isEmpty()) {
                return mData.get(position);
            }
            return null;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int spanCount = gridLayoutManager.getSpanCount();
                        if (isNeedSingleLine(position)) {
                            return spanCount;
                        }
//                        if (position % spanCount == 0) {
//                            return spanCount - 1;
//                        }
                        return 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(MyVH holder) {
            if (isNeedSingleLine(holder.getLayoutPosition())) {
                ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                    p.setFullSpan(true);
                }
            }
        }

        public int getItemPosition(Item item) {
            return mRealRecList.indexOf(item);
        }

        private boolean isNeedSingleLine(int position) {
            int viewType = getItemViewType(position);
            return viewType == Item.TYPE_BIG_HEADER
                    || viewType == Item.TYPE_HEADER
                    || viewType == Item.TYPE_PRODUCT
                    || viewType == Item.TYPE_REC_HEADER;
        }

        public class MyVH extends RecyclerView.ViewHolder {

            public MyVH(View itemView) {
                super(itemView);
            }
        }

        public class HeaderVH extends MyVH {

            public HeaderVH(View itemView) {
                super(itemView);
            }
        }

        public class ProductVH extends MyVH {

            public ProductVH(View itemView) {
                super(itemView);
            }
        }

        public class RecHeaderVH extends MyVH {

            TextView tvLike;
            LinearLayout llLike;

            public RecHeaderVH(View itemView) {
                super(itemView);
//                tvLike = itemView.findViewById(R.id.cart_tv_rec_header_center);
//                llLike = itemView.findViewById(R.id.cart_ll_rec_header);
            }

            public void bind() {
                RecHeader rec = (RecHeader) getItem(getLayoutPosition());
//                tvLike.setVisibility(!rec.hasImage ? View.VISIBLE : View.GONE);
//                llLike.setVisibility(rec.hasImage ? View.VISIBLE : View.GONE);
            }
        }

        public class RecVh extends MyVH {

            TextView tvHeader, tv1, tv2, tv3, tv4;

            public RecVh(View itemView) {
                super(itemView);
                tvHeader = itemView.findViewById(R.id.cart_tv_rec_header);
                tv1 = itemView.findViewById(R.id.cart_tv_rec_1);
                tv2 = itemView.findViewById(R.id.cart_tv_rec_2);
                tv3 = itemView.findViewById(R.id.cart_tv_rec_3);
                tv4 = itemView.findViewById(R.id.cart_tv_rec_4);
            }

            public void bind() {
                Rec rec = (Rec) getItem(getLayoutPosition());

                tvHeader.setText(getLayoutPosition() + "");
                tv1.setVisibility(rec.show1 ? View.VISIBLE : View.GONE);
                tv2.setVisibility(rec.show2 ? View.VISIBLE : View.GONE);
                tv3.setVisibility(rec.show3 ? View.VISIBLE : View.GONE);
                tv4.setVisibility(rec.show4 ? View.VISIBLE : View.GONE);
            }
        }
    }
}
