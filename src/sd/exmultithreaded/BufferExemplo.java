/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.exmultithreaded;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caique
 */
public class BufferExemplo implements Buffer {

    private Lock lockDeAcesso = new ReentrantLock();
    private Condition podeGravar = lockDeAcesso.newCondition();
    private Condition podeLer = lockDeAcesso.newCondition();

    private int buffer = -1;
    private boolean ocupado = false;

    @Override
    public void set(int valor) {

        lockDeAcesso.lock();

        try {
            while (ocupado) {
                System.out.println("Produtor tentar gravar...");
                mostrarEstado("Buffer Cherio. Produtor Aguardando...");
                podeGravar.await();
            }

            buffer = valor;
            ocupado = true;
            mostrarEstado("Produtor grava: " + buffer);
            podeLer.signal();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lockDeAcesso.unlock();
        }

//        System.out.printf("Produtor grava: \t%2d\n", valor);
//        buffer = valor;
    }

    @Override
    public int get() {
        
        int valorLido = 0;
        lockDeAcesso.lock();
        
        try{
            while(!ocupado){
                System.out.println("Consumidor tentar ler...");
                mostrarEstado("Buffer vazio. Consumidor Aguardando...");
                podeLer.await();                
            }
            ocupado = false;
            valorLido = buffer;
            mostrarEstado("Consumidor lê: " + valorLido);
            podeGravar.signal();
        } catch (InterruptedException ex) {
            Logger.getLogger(BufferExemplo.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            lockDeAcesso.unlock();
        }
        
        return valorLido;

//        System.out.printf("Consumidor lê: \t%2d\n", buffer);
//        return buffer;
    }

    public void mostrarEstado(String operacao) {
        System.out.printf("%-40s%d\t\t%b\n\n", operacao, buffer, ocupado);
    }
}
