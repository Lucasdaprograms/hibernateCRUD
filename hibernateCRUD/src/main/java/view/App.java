package view;

import javax.swing.JFrame;

import controller.ClienteJpaDao;
import model.Cliente;

public class App
{
    public static void main( String[] args )
    {
    	 Cliente cliente = new Cliente();
         cliente.setCpf("372.468.423-53");
         cliente.setId(1);
         cliente.setNome("Lucas Mendes");
         cliente.setRg("52.563.456-2");
         ClienteJpaDao.getInstance().merge(cliente);
         System.out.println("Objetos salvo com sucesso!!!");
    }
}