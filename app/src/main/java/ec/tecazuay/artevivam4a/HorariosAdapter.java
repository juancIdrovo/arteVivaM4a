package ec.tecazuay.artevivam4a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Asignatura;
import ec.tecazuay.artevivam4a.modelo.Horarioss;
import ec.tecazuay.artevivam4a.modelo.Matricula;
import ec.tecazuay.artevivam4a.modelo.Profesor;

public class HorariosAdapter extends RecyclerView.Adapter<HorariosAdapter.HorariosViewHolder> {

    private List<Horarioss> horarios;
    public HorariosAdapter(List<Horarioss> horarios) {
        this.horarios = horarios;
    }
    @NonNull
    @Override
    public HorariosAdapter.HorariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
        return new HorariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorariosViewHolder holder, int position) {
        Horarioss horario = horarios.get(position);

        // Mostrar el nombre de la asignatura en lugar del código
        holder.tvAsignaturaH.setText("Asignatura: " + horario.getNombreAsignatura());

        // Mostrar el día del horario
        holder.tvDia.setText("Día: " + horario.getDia());

        // Mostrar el horario de inicio y fin
        holder.tvHoraIni.setText("Hora de inicio: " + horario.getHora_Inicio());
        holder.tvHoraFin.setText("Hora Fin: " + horario.getHora_fin());
    }


    @Override
    public int getItemCount() {
        return horarios != null ? horarios.size() : 0;
    }
    public void setHorarios(List<Horarioss> horarios) {
        this.horarios = horarios;
    }

    public static class HorariosViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvDia;
        public TextView tvHoraIni;
        public TextView tvHoraFin;
        public TextView tvAsignaturaH;
        public TextView tvCedulaP;
        public TextView tvNombreP;

        public HorariosViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar vistas aquí

            tvAsignaturaH = itemView.findViewById(R.id.tvAsignaturaH);
            tvDia= itemView.findViewById(R.id.tvDia);
            tvHoraIni = itemView.findViewById(R.id.tvHoraIni);
            tvHoraFin = itemView.findViewById(R.id.tvHoraFin);
        }
    }
}
