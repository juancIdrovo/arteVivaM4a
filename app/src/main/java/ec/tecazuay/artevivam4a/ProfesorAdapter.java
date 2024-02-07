package ec.tecazuay.artevivam4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.tecazuay.artevivam4a.R;
import ec.tecazuay.artevivam4a.modelo.Profesor;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder> {
    private List<Profesor> listaProfesores;

    // Constructor y otros métodos necesarios

    // Nuevo método para actualizar la lista de profesores
    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    static class ProfesorViewHolder extends RecyclerView.ViewHolder {
        // Referencias a los elementos de la vista de cada elemento de la lista
        TextView nombreTextView;
        TextView tituloTextView;

        ProfesorViewHolder(View itemView) {
            super(itemView);
            // Inicializa las vistas aquí
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
        }
    }

    @NonNull
    @Override
    public ProfesorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño del elemento de la lista y crea el ViewHolder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ec.tecazuay.artevivam4a.R.layout.item_profesor, parent, false);

        return new ProfesorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfesorViewHolder holder, int position) {
        // Asigna los datos del profesor a las vistas dentro del ViewHolder
        Profesor profesor = listaProfesores.get(position);
        holder.nombreTextView.setText(profesor.getNombres());
        holder.tituloTextView.setText(profesor.getTitulo());
    }

    @Override
    public int getItemCount() {
        // Devuelve el tamaño de la lista de profesores
        return listaProfesores != null ? listaProfesores.size() : 0;
    }
}
