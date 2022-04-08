package ReflectionTest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import fr.fms.business.IBankBusinessImpl;

public class Introspection {

	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		IBankBusinessImpl banBusiness = new IBankBusinessImpl();
		
		//Recuperation du nom de la class d'un objet, grace a la methode .getClass() de la class Object.
		System.out.println("Nom complet de la class :" + banBusiness.getClass().getName());
		
		String nomDeMaClass = banBusiness.getClass().getName();
		System.out.println("-------------------------------");
				
		//Constructeur .forName() de la class Class a partir de la chaine de charactère representant le nom de la class
		//C'est une instanciation par reflection.
		
		//Class c = Class.forName("fr.fms.business.IBankBusinessImpl"); 
		Class c = Class.forName(nomDeMaClass); //est équivalent à la lign du dessus

		//Recuperation de tous les champs de la class (et de leur modificateur), meme les champs privés.
		System.out.println("Listes des attributs de la class : " + banBusiness.getClass().getSimpleName());
		Field[] attributes = c.getDeclaredFields();
		for(Field attribute : attributes) {
			System.out.println(Modifier.toString(attribute.getModifiers()) + "\t" + attribute.getName() + " " + attribute.getType().getSimpleName());
			
		}
		System.out.println("-------------------------------");
		//Recuperation de toutes les methodes (et de leur modificateur) de la class dans un tableau 
		Method[] methods = c.getDeclaredMethods();
		for(Method method : methods) {
			System.out.print(Modifier.toString( method.getModifiers()) + " : " + method.getName());
			Type[] types = method.getParameterTypes();
			for(Type type :types)
					System.out.println( " type du parametre : " + type.getTypeName());
		}
		
	}
}
