package com.example.musics.adaptador;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musics.R;
import com.example.musics.model.Disco;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscoAdapters extends RecyclerView.Adapter<DiscoAdapters.DiscoViewHolder> {
    private List<Disco> discos;
    private  List<Disco> DiscosItems;
    private RecyclerDiscoClick discoClick;
   private String domain_image="http://localhost/servicio1040/drawable/";
    //

    public DiscoAdapters(List<Disco> discos,RecyclerDiscoClick discoClick) {
        this.discos = discos;
        this.discoClick=discoClick;
        this.DiscosItems=new ArrayList<>();
        DiscosItems.addAll(discos);
    }



    @NonNull
    @Override
    public DiscoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this.context=parent.getContext();
        View layout_item= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.discos_item,parent,false);

        return new DiscoViewHolder(layout_item);
    }



    @Override
    public void onBindViewHolder(@NonNull final DiscoViewHolder holder, final int position) {
        final Disco item=discos.get(position);

        //von xamp
        if(item.getImag()>1){
            holder.imaDisco.setImageResource(item.getImag());
        }
        else {
            Picasso.get().load(domain_image + item.getImagen())
                    .into(holder.imaDisco);
        }
        holder.artist.setText(item.getArtista());
        holder.descrip.setText(item.getDescripcion());
        //

        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        discoClick.itemClick(item);

    }
});



    }

    @Override
    public int getItemCount() {
        return discos.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            discos.clear();
            discos.addAll(DiscosItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                discos.clear();
                List<Disco> collect = DiscosItems.stream()
                        .filter(i -> i.getArtista().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                discos.addAll(collect);
            }
            else {
                discos.clear();
                for (Disco i : DiscosItems) {
                    if (i.getArtista().toLowerCase().contains(strSearch)) {
                        discos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class DiscoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imaDisco;
        private TextView artist;
        private TextView descrip;
        public DiscoViewHolder(@NonNull View itemView_1) {
            super(itemView_1);


            imaDisco = itemView.findViewById(R.id.images);
            artist = itemView.findViewById(R.id.txtArtistas);
            descrip = itemView.findViewById(R.id.txtDescris);

        }

    }
    public interface  RecyclerDiscoClick{
        void itemClick(Disco item);
    }

}

