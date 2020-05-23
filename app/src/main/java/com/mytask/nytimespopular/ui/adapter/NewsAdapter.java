package com.mytask.nytimespopular.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mytask.nytimespopular.base.BaseViewHolder;
import com.mytask.nytimespopular.databinding.CellNewsAdapterBinding;
import com.mytask.nytimespopular.helpers.interfaces.RecyclerClick;
import com.mytask.nytimespopular.helpers.utils.GeneralFunction;
import com.mytask.nytimespopular.model.ResultResponse;

import java.util.List;

/**
 * Adapter of News Master
 */
public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    Context mContext;
    RecyclerClick mRecyclerClick;
    private List<ResultResponse> mDataList;


    public NewsAdapter(Context mContext, List<ResultResponse> menuItemsList,
                       RecyclerClick mRecyclerClick) {
        this.mDataList = menuItemsList;
        this.mContext = mContext;
        this.mRecyclerClick = mRecyclerClick;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeCellViewHolder(CellNewsAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void addItems(List<ResultResponse> menuItemsList) {
        if (this.mDataList.size() > 0)
            this.mDataList.clear();
        this.mDataList = menuItemsList;
    }


    public class HomeCellViewHolder extends BaseViewHolder {

        private final CellNewsAdapterBinding mBinding;

        public HomeCellViewHolder(CellNewsAdapterBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            GeneralFunction.loadImage(mContext, mDataList.get(position).
                    getMedia().get(0).getMediaMetadata().get(0).getUrl(), this.mBinding.imgNews);
            this.mBinding.actvTitle.setText(mDataList.get(position).getTitle());
            this.mBinding.actvAuthor.setText(mDataList.get(position).getByline());
            this.mBinding.actvDetails.setText(mDataList.get(position).getAbstract());
            this.mBinding.actvDate.setText(mDataList.get(position).getPublishedDate());
        }
    }
}