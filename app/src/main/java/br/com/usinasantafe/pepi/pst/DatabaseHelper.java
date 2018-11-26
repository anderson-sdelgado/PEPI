package br.com.usinasantafe.pepi.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pepi.to.tb.estaticas.DataTO;
import br.com.usinasantafe.pepi.to.tb.estaticas.EPITO;
import br.com.usinasantafe.pepi.to.tb.estaticas.FuncTO;
import br.com.usinasantafe.pepi.to.tb.estaticas.MotivoTO;
import br.com.usinasantafe.pepi.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.pepi.to.tb.variaveis.EntregaEPITO;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, Database.FORCA_DB_NAME,
				null, Database.FORCA_BD_VERSION);
		
		// TODO Auto-generated constructor stub
		instance = this;
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		// TODO Auto-generated method stub
		
		try{
			
			TableUtils.createTable(cs, EPITO.class);
			TableUtils.createTable(cs, FuncTO.class);
			TableUtils.createTable(cs, MotivoTO.class);
			TableUtils.createTable(cs, EntregaEPITO.class);
			TableUtils.createTable(cs, ConfiguracaoTO.class);
			TableUtils.createTable(cs, DataTO.class);
			
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
