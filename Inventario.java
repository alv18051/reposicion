
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Inventario {

   
    public class Producto implements Comparable{
        //Descripcion es la descripcion del producto
        String descripcion;
        //Categoria es la categoria del producto
        String categoria;
        
       
        public Producto(String cat,String desc){
            this.descripcion = desc;
            this.categoria = cat;
        }

       
        public String getCategoria() {
            return categoria;
        }

        
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

       
        public String getDescripcion() {
            return descripcion;
        }

        
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        
        
        public String describe(){
            return "Categoria=" + categoria+ ", descripcion=" + descripcion;
        }

        
        @Override
        public String toString() {
            return "Categoria=" + categoria+ ", descripcion=" + descripcion + ", cantidad = "+inventario.get(this);
        }

        
        @Override
        public int compareTo(Object t) {
            Producto p = (Producto) t;
            int w = this.categoria.compareTo(p.getCategoria());
            //Sorting by first name if last name is same d
            return w == 0 ? this.descripcion.compareTo(p.getDescripcion()) : w;
        }
        
        
    }
    //inventario es un Mapa que mapea productos a enteros, es decir, cada producto a su cantidad.
    private Map<Producto,Integer> inventario;

    
    public Inventario(String filename, int tipo) {
        switch(tipo){
            case 0:
                inventario = new HashMap<Producto, Integer>();
                break;
            case 1:
                inventario = new TreeMap<Producto,Integer>();
                break;
            case 2:
                inventario = new LinkedHashMap<Producto,Integer>();
                break;
            default:
                break;
        }
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] prod = parseData(data);
              Producto producto = new Producto(prod[0].trim(),prod[1].trim());
              inventario.put(producto, 10);
            }
            
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    
    private String[] parseData(String str){	
    String categoria, producto;
    Scanner lineScanner = new Scanner(str);
    lineScanner.useDelimiter("\\|");
    String[] r = new String[2];
    categoria = lineScanner.next();
    producto = lineScanner.next();
    r[0] = categoria;
    r[1] = producto;
    lineScanner.close();
    return r;
    
    }
    
    
    public void agregarProducto(String cat, String desc, int cantidad)
	{	
            Producto producto = new Producto(cat,desc);
		if(inventario.get(producto) == null)
			inventario.put(producto, 0);
		cantidad += inventario.get(producto);
		inventario.put(producto, cantidad);
	}
    
    
    public String getCategoria(String desc){
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
            Producto p = itr.next();
            if(p.getDescripcion().equalsIgnoreCase(desc)){
                return p.getCategoria();
            }
        }
        return "Error: producto desconocido";
    }
    
    
    public String getDatos(){
        String res = "";
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
        res += itr.next().toString()+ "\n ";
        }
        return res;
    }
    
    
    public String getDatosOrdenados(){
        String res = "";
        SortedSet<Producto> ss = new TreeSet<>(inventario.keySet());
        for(Producto p : ss){
        res += p.toString()+ "\n ";
        }
        return res;
    }
    
   
    public String getPyC(){
        String res = "";
        Iterator<Producto> itr = inventario.keySet().iterator();
        while(itr.hasNext()){
        res += itr.next().describe()+ "\n ";
        }
        return res;
    }

   
    public String getPyCO(){
        String res = "";
        SortedSet<Producto> ss = new TreeSet<>(inventario.keySet());
        for(Producto p : ss){
            if(inventario.get(p)!=0){
                res += p.describe()+ "\n ";
            }  
        }
        return res;
    }
}
