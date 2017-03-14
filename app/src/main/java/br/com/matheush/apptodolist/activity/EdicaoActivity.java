package br.com.matheush.apptodolist.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.matheush.apptodolist.R;
import br.com.matheush.apptodolist.dao.TarefaDao;
import br.com.matheush.apptodolist.fragment.DatePickerFragment;
import br.com.matheush.apptodolist.fragment.TimePickerFragment;
import br.com.matheush.apptodolist.model.Tarefa;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EdicaoActivity extends AppCompatActivity {

    public static final String TAG = "LogX_Edicao";

    Tarefa tarefa;
    @BindView(R.id.edicao_et_tarefa)
    EditText etTarefa;
    @BindView(R.id.edicao_et_hora)
    EditText etHora;
    @BindView(R.id.edicao_et_data)
    EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        tarefa = new TarefaDao().getObjeto(intent.getIntExtra("index", 0));
        Log.d(TAG, tarefa.toString());

        etTarefa.setText(tarefa.getTarefa());
        etTarefa.setSelection(tarefa.getTarefa().length());

        etHora.setFocusable(false);
        if (!tarefa.getHora().equals("")) {
            etHora.setText(tarefa.getHora());
        } else {
            etHora.setText(getResources().getText(R.string.nao_definida));
        }

        etData.setFocusable(false);
        if (!tarefa.getData().equals("")) {
            etData.setText(tarefa.getData());
        } else {
            etData.setText(getResources().getText(R.string.nao_definida));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edicao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //verifica se tem alteração, pergunta e sai
                Tarefa tarefaEditada = new Tarefa();
                tarefaEditada.setId(tarefa.getId());
                tarefaEditada.setTarefa(etTarefa.getText().toString());

                finish();
                return true;
            case R.id.action_salvar_item:
                //salva item
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.edicao_et_hora, R.id.edicao_et_data})
    public void onClick(View view) {
        DialogFragment newFragment;
        switch (view.getId()) {
            case R.id.edicao_et_hora:
                //chama fragment time picker
                newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
                break;
            case R.id.edicao_et_data:
                //chama fragment date picker
                newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
                break;
        }
    }
}
