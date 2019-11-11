/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.exmultithreaded;

/**
 *
 * @author caique
 */
public class BufferExemplo implements Buffer{
    
    private int buffer = -1;

    @Override
    public void set(int valor) {
        System.out.printf("Produtor grava: \t%2d\n", valor);
        
        buffer = valor;
    }

    @Override
    public int get() {
        System.out.printf("Consumidor lê: \t%2d\n", buffer);
        
        return buffer;

    }
    
}
