package ec.tecazuay.artevivam4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Asignatura;
import ec.tecazuay.artevivam4a.modelo.Matricula;
import ec.tecazuay.artevivam4a.modelo.Profesor;

public class MatriculasAdapter extends RecyclerView.Adapter<MatriculasAdapter.MatriculaViewHolder> {
    private List<Matricula> matriculas;

    public MatriculasAdapter(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @NonNull
    @Override
    public MatriculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matricula, parent, false);
        return new MatriculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatriculaViewHolder holder, int position) {
        Matricula matricula = matriculas.get(position);
        Asignatura asignatura = matricula.getAsignatura();
        Profesor profesor = matricula.getProfesor(); // Nuevo

        if (asignatura != null) {
            holder.tvAsignatura.setText("Asignatura: " + asignatura.getNombre());
            holder.tvDescripcion.setText("Descripción: " + asignatura.getDescripcion());
        }

        if (profesor != null) {
            holder.tvnomApe.setText("Docente: " + profesor.getNombres() + " " + profesor.getApellidos());
        }

        holder.tvEstado.setText("Estado: " + matricula.getEstado());
        holder.tvCalificacion.setText("Calificación: " + matricula.getCalificacion());
        holder.tvFechaInicio.setText("Empezó en: " + matricula.getFechaInicioFormatted());
        holder.tvFechaFin.setText("Termina en: " + matricula.getFechaFinFormatted());
    }

    @Override
    public int getItemCount() {
        return matriculas != null ? matriculas.size() : 0;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    // ViewHolder para la vista de cada elemento de matrícula
    public static class MatriculaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAsignatura;
        public TextView tvEstado;
        public TextView tvCalificacion;
        public TextView tvFechaInicio;
        public TextView tvFechaFin;
        public TextView tvDescripcion;
        public TextView tvnomApe;

        public MatriculaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar vistas aquí
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvCalificacion = itemView.findViewById(R.id.tvCalificacion);
            tvFechaInicio = itemView.findViewById(R.id.tvFechaInicio);
            tvFechaFin = itemView.findViewById(R.id.tvFechaFin);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvnomApe = itemView.findViewById(R.id.tvnomApe);
        }
    }
}

