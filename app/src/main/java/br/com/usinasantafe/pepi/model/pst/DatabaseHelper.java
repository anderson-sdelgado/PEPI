package br.com.usinasantafe.pepi.model.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pepi.model.bean.estaticas.DataBean;
import br.com.usinasantafe.pepi.model.bean.estaticas.EPIBean;
import br.com.usinasantafe.pepi.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pepi.model.bean.estaticas.MotivoBean;
import br.com.usinasantafe.pepi.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pepi_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;

	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, FORCA_DB_NAME,null, FORCA_BD_VERSION);
		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		try{
			
			TableUtils.createTable(cs, EPIBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, MotivoBean.class);
			TableUtils.createTable(cs, EntregaEPIBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, DataBean.class);
			
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				//TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
