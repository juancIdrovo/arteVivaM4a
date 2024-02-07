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

    public void setListaProfesores(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    static class ProfesorViewHolder extends RecyclerView.ViewHolder {
        // Referencias a los elementos de la vista de cada elemento de la lista
        TextView nombreTextView;
        TextView tituloTextView;
        TextView CorreoTextView;
        TextView telfTextView;
        TextView cedulaTextView;
        ProfesorViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            cedulaTextView = itemView.findViewById(R.id.CedulaTextView);
            telfTextView = itemView.findViewById(R.id.TelfTextView);
            CorreoTextView = itemView.findViewById(R.id.CorreoTextView);
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
        holder.cedulaTextView.setText("Cedula: " + profesor.getCedula_profesor_fk());
        holder.nombreTextView.setText("Profesor: " + profesor.getNombres() + " " + profesor.getApellidos());
        holder.tituloTextView.setText("Titulo: " + profesor.getTitulo());
        holder.CorreoTextView.setText("Correo: " + profesor.getCorreo());
    }

    @Override
    public int getItemCount() {
        // Devuelve el tamaño de la lista de profesores
        return listaProfesores != null ? listaProfesores.size() : 0;
    }
}
