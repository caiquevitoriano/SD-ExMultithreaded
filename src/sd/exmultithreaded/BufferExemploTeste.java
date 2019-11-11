/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.exmultithreaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author caique
 */
public class BufferExemploTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ExecutorService teste = Executors.newFixedThreadPool(2);
        Buffer localizacaoCompartilhada = new BufferExemplo();
        try {
            teste.execute(new Produtor(localizacaoCompartilhada));
            teste.execute(new Consumidor(localizacaoCompartilhada));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        teste.shutdown();
    }

}
