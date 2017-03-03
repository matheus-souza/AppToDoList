package br.com.matheush.apptodolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.matheush.apptodolist.R;
import br.com.matheush.apptodolist.model.Tarefa;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matheush on 18/02/17.
 */

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    private Context context;
    private List<Tarefa> tarefaList;
    private OnItemClickListener onItemClicked;
    private OnItemLongClickListener onItemLongClicked;

    public TarefaAdapter(Context context, List<Tarefa> tarefaList, OnItemClickListener onItemClicked, OnItemLongClickListener onItemLongClicked) {
        this.context = context;
        this.tarefaList = tarefaList;
        this.onItemClicked = onItemClicked;
        this.onItemLongClicked = onItemLongClicked;
    }

    @Override
    public int getItemCount() {
        return this.tarefaList != null ? this.tarefaList.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_tarefa_row, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Atualiza a view
        Tarefa t = tarefaList.get(position);

        holder.tvTarefa.setText(t.getTarefa());

        if (!t.getData().equals("") && !t.getHora().equals("")) {
            holder.tvDataHora.setText(t.getData() + " às " + t.getHora());
        } else {
            holder.tvDataHora.setText("");
        }

        //Click
        if (onItemClicked != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClicked.onItemClicked(holder.itemView, position);
                }
            });
        }

        //Long press
        if (onItemLongClicked != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClicked.onItemLongClicked(position);
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClicked(View view, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_tv_tarefa)
        TextView tvTarefa;
        @BindView(R.id.card_tv_data_hora)
        TextView tvDataHora;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}