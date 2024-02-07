package ec.tecazuay.artevivam4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Profesor;

public class DocenteAdapter extends RecyclerView.Adapter<DocenteAdapter.DocenteViewHolder> {
    private List<Profesor> profesores;

    public DocenteAdapter(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    @NonNull
    @Override
    public DocenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_busca_profe, parent, false);
        return new DocenteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocenteViewHolder holder, int position) {
        Profesor profesor = profesores.get(position);
        // Mostrar nombres y apellidos en un solo campo
        holder.tvNombre.setText("Docente: "+ profesor.getNombres() + " " + profesor.getApellidos());
        // Mostrar correo en otro campo
        holder.tvCorreo.setText("Correo Electrónico: " + profesor.getCorreo());
        // Mostrar teléfono en otro campo
        holder.tvTelefono.setText("Celular: " + profesor.getTelf());
        // Mostrar título en otro campo
        holder.tvTitulo.setText("Título: " + profesor.getTitulo());
    }

    @Override
    public int getItemCount() {
        return profesores.size();
    }

    public static class DocenteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCorreo, tvTelefono, tvTitulo;

        public DocenteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
        }
    }
}

