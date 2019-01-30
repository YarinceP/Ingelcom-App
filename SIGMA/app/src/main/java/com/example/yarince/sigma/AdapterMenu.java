package com.example.yarince.sigma;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolderMenu> {

    ArrayList<BotonMenu> listMenu;


    public AdapterMenu(ArrayList<BotonMenu> listMenu) {
        this.listMenu = listMenu;
    }


    @NonNull
    @Override
    public AdapterMenu.ViewHolderMenu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflamos el View
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_menu, null, false);
        //Retornamos  mediante la instancia de ViewHolder
        return new ViewHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.ViewHolderMenu viewHolderMenu, int i) {
        viewHolderMenu.title.setText(listMenu.get(i).getTitulo());
        viewHolderMenu.description.setText(listMenu.get(i).getDescripcion());
        viewHolderMenu.imageView.setImageResource(listMenu.get(i).getImagen());
        viewHolderMenu.background.setImageResource(listMenu.get(i).getBackground());
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolderMenu extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        //CardView cardView;
        ImageView imageView;
        ImageView background;


        public ViewHolderMenu(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.idTitle);
            description = (TextView) itemView.findViewById((R.id.idDescription));
            imageView = (ImageView) itemView.findViewById(R.id.idImageView);
            //cardView = (CardView) itemView.findViewById(R.id.idCardView);
            background = (ImageView) itemView.findViewById(R.id.idBackgroundImageView);
        }

    }
}
