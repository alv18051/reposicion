
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
        Inventario inventario;		
        Object[] options = {"HashMap","TreeMap", "LinkedHashMap"};
        Object[] functions = {"agregar", "mostrar categoria",
        		"mostrar datos", "mostrar datos ordenados",
        		"mostrar todo", "mostrar todo ordenado", "tiempo de ejecucion","Salir"};
        
        int map =JOptionPane.showOptionDialog(null,
                    "Que tipo de MAP quiere usar?",
                    "Tipo de MAP",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        inventario = new Inventario("ListadoProducto.txt",map);
       
       int menu = 0;
       
       while(menu != 7) {
       int op = JOptionPane.showOptionDialog(null,
                "que desea realizar?",
                "Acciones disponibles",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                functions,
                functions[0]);
       if(op == 0) {
    	    
           String cat = JOptionPane.showInputDialog(null,"Ingrese la categoria:");
           String desc = JOptionPane.showInputDialog(null,"Ingrese la descripcion:");
           int cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cuantas de estas desea ingresar "));
           inventario.agregarProducto(cat,desc, cant);
    	   System.out.println("Se ha agregado exitosamente");
       } else if(op== 1) {
    	   
           String desc = JOptionPane.showInputDialog(null,"Ingrese la descripcion:");
           String categoria = inventario.getCategoria(desc);
    	   System.out.println("La categoria es: "+ categoria);
       }else if(op == 2) {
    	   //Mostrar datos del producto
    	   System.out.println("Los datos son: ");
           System.out.println(inventario.getDatos());
       }else if(op == 3) {
    	   // mostrar datos del producto ordenados
    	   System.out.println("Los datos en orden son:");
           System.out.println(inventario.getDatosOrdenados());
       }else if(op == 4) {
    	   // se imprime el inventario
    	   System.out.println("Todo: ");
           System.out.println(inventario.getPyC());
       }else if(op == 5) {
    	   //inventario ordenado
    	   System.out.println("Todo ordenado es: ");
           System.out.println(inventario.getPyCO());
       }else if(op == 6){
           //Calcula y devuelve el tiempo de ejecución promedio para cada árbol.
           Inventario inventario1;
           long[] tiempos = new long[3];
           inventario1 = new Inventario("ListadoProducto.txt",0);
           long t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           long t1 = System.nanoTime()-t0;
           tiempos[0] = t1/100;
           inventario1 = new Inventario("ListadoProducto.txt",1);
           t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           t1 = System.nanoTime()-t0;
           tiempos[1] = t1/100;
           inventario1 = new Inventario("ListadoProducto.txt",2);
           t0 = System.nanoTime();
           for(int i = 0; i<100; i++){
            inventario1.getDatosOrdenados();   
           }
           t1 = System.nanoTime()-t0;
           tiempos[2] = t1/100;
           
           System.out.println("Los tiempos de ejecucion fueron:\n"
                   + " Hashmap: "+ tiempos[0]+ " \n"
                           + " Treemap: "+tiempos[1]+ " \n"
                                   + " LinkedHashmap: "+tiempos[2]+" todo en nanosegundos");
       }else{
    	   menu = 7;
    	   
       }
        
   }
	
	}
}
