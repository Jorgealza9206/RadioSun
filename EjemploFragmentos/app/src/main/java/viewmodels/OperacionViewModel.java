package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import clases.Operacion;

public class OperacionViewModel extends ViewModel {

    public MutableLiveData<Operacion> getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion.setValue(operacion);
    }

    private MutableLiveData<Operacion> operacion = new MutableLiveData<>()
}
