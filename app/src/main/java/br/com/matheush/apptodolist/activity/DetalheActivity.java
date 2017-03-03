package br.com.matheush.apptodolist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

    public void atualizaCampos() {
        tvTarefa.setText(tarefa.getTarefa());

        if (!tarefa.getHora().equals("")) {
            tvHora.setText(tarefa.getHora());
        } else {
            tvHora.setText("Hora não definida");
        }

        if (!tarefa.getData().equals("")) {
            tvData.setText(tarefa.getData());
        } else {
            tvData.setText("Data não definida");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_editar_item:
                //chama activity de edição
                return true;
            case R.id.action_excluir_item:
                new TarefaDao().deleteObjeto(tarefa);
                Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
