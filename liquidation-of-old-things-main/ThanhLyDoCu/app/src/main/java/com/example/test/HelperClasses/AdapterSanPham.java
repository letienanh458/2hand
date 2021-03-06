package com.example.test.HelperClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.User.MainActivityProdectDetail;
import com.example.test.User.ProductDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.ViewHolder> {
    @NonNull
    private Context mcontext;
    ArrayList<Hinhanh> list;
    ArrayList<Hinhanh> filterList;

    TiengViet xuLyChuoiTiengViet = new TiengViet();
    public AdapterSanPham(Context mcontext, ArrayList<Hinhanh> list) {
        this.mcontext = mcontext;
        this.filterList = list;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.auction_card_design, parent, false);
        return new ViewHolder(view);
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String url = filterList.get(position).getLink();
        Picasso.get().load(url).into(holder.imvHSP);
        holder.txtTSP.setText(filterList.get(position).getTenhinh());
        holder.Gia.setText(filterList.get(position).getGia());
      //  holder.Khuvuc.setText(filterList.get(position).getKhuvuc());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mcontext, ProductDetail.class);
                intent.putExtra("ten",filterList.get(position).getTenhinh());
                intent.putExtra("hinh",filterList.get(position).getLink());
                intent.putExtra("gia",filterList.get(position).getGia());
                intent.putExtra("noidung",filterList.get(position).getNoidung());
                intent.putExtra("sdt",filterList.get(position).getSdt());
                intent.putExtra("danhmuc",filterList.get(position).getTendm());
                intent.putExtra("tinhtrang",filterList.get(position).getTinhtrang());
                intent.putExtra("khuvuc",filterList.get(position).getKhuvuc());
                intent.putExtra("idcustomer",filterList.get(position).getIdcustomer());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Object coordinatorlayout;
        ImageView imvHSP;
        TextView txtTSP,Gia,Khuvuc;
        androidx.coordinatorlayout.widget.CoordinatorLayout t;

        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHSP = itemView.findViewById(R.id.auction_img);
            txtTSP = itemView.findViewById(R.id.auction_title);
            Gia=itemView.findViewById(R.id.auction_price);
           // Khuvuc=itemView.findViewById(R.id.sur_pro_local);
            relativeLayout = itemView.findViewById(R.id.autoard1);
        }
    }
    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            /// kh???i t???o bi???n result
            FilterResults filterResults= new FilterResults();
            String keySearch = constraint.toString();
            /// khi keysearch co gia tri
            if (keySearch != null && keySearch.toString().length() > 0) {
                /// th?? m??nh kh???i t???o list tr???ng ????? add d??? li???u sao khi check v??o
                ArrayList<Hinhanh> filteredList = new ArrayList<>();
                String pattrn= keySearch.toLowerCase().trim();
                for(Hinhanh item : list){
                    /// check d??? li???u ????? add
                    if (xuLyChuoiTiengViet.ConvertString(item.getTenhinh().toLowerCase())
                            .contains(xuLyChuoiTiengViet.ConvertString(pattrn))) {
                        filteredList.add(item);
                    }
                }
                /// g??n v??o gi?? tr??? sao khi check xong
                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
            }
            else{
                /// g??n l???i gi?? tr??? all
                filterResults.values = list;
                filterResults.count = list.size();
//                synchronized (list) {
//                    filterResults.values = list;
//                    filterResults.count = list.size();
//                }
            }
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterList = (ArrayList<Hinhanh>) results.values;
            notifyDataSetChanged();
        }
    };
}
