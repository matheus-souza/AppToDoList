package br.com.matheush.apptodolist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.matheush.apptodolist.R;
import br.com.matheush.apptodolist.dao.TarefaDao;
import br.com.matheush.apptodolist.model.Tarefa;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheActivity extends AppCompatActivity {

    public static final String TAG = "LogX_Detalhe";

    int index;
    Tarefa tarefa;

    @BindView(R.id.detalhe_tv_tarefa)
    TextView tvTarefa;
    @BindView(R.id.detalhe_tv_hora)
    TextView tvHora;
    @BindView(R.id.detalhe_tv_data)
    TextView tvData;
    @BindView(R.id.detalhe_tv_titulo_conclusao)
    TextView tvTituloConclusao;
    @BindView(R.id.detalhe_tv_hora_conclusao)
    TextView tvHoraConclusao;
    @BindView(R.id.detalhe_tv_data_conclusao)
    TextView tvDataConclusao;
    @BindView(R.id.detalhe_card_conclusao)
    CardView cardConclusao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        Log.d(TAG, "id: " + index);

        tarefa = new TarefaDao().getObjeto(index);

        atualizaCampos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaCampos();
    }

    public void atualizaCampos() {
        tvTarefa.setText(tarefa.getTarefa());
        tvHora.setText(tarefa.getHora());
        tvData.setText(tarefa.getData());

        if (!tarefa.isAtiva()) {
            tvTituloConclusao.setVisibility(View.VISIBLE);
            cardConclusao.setVisibility(View.VISIBLE);

            tvHoraConclusao.setText(tarefa.getHoraConclusao());
            tvDataConclusao.setText(tarefa.getDataConclusao());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!tarefa.isAtiva()) {
            menu.removeItem(R.id.action_editar_item);
            menu.removeItem(R.id.action_concluir_item);

            MenuItem item = menu.findItem(R.id.action_excluir_item);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_editar_item:
                Intent intent = new Intent(DetalheActivity.this, EdicaoActivity.class);
                intent.putExtra("index", tarefa.getId());
                startActivity(intent);
                return true;
            case R.id.action_excluir_item:
                new TarefaDao().deleteObjeto(tarefa);
                Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.action_concluir_item:
                SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                Date dataAtual = calendar.getTime();

                String horaAtualString = horaFormat.format(dataAtual);
                String dataAtualString = dataFormat.format(dataAtual);

                Tarefa tarefaEditada = new Tarefa();
                tarefaEditada.setId(tarefa.getId());
                tarefaEditada.setTarefa(tarefa.getTarefa());
                tarefaEditada.setHora(tarefa.getHora());
                tarefaEditada.setData(tarefa.getData());
                tarefaEditada.setAtiva(false);
                tarefaEditada.setHoraConclusao(horaAtualString);
                tarefaEditada.setDataConclusao(dataAtualString);

                new TarefaDao().atualizaObjeto(tarefaEditada);

                Toast.makeText(getApplicationContext(), "Tarefa concluída com sucesso!", Toast.LENGTH_SHORT).show();

                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
