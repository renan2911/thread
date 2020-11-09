import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread{
	
	public static ConcurrentLinkedQueue<ObjetoFilaThread>  pilha_fila = 
			new ConcurrentLinkedQueue<ObjetoFilaThread>();
	
	
	public static void add(ObjetoFilaThread objetoFilaThread) {
		pilha_fila.add(objetoFilaThread);
	}
	
	
	@Override
	public void run() {
		Iterator interacao = pilha_fila.iterator();
		
		synchronized (interacao) {
			while(interacao.hasNext()) {
				
				ObjetoFilaThread processar = (ObjetoFilaThread) interacao.next();
				
				System.out.println("--------------------------");
				System.out.println(processar.getEmail());
				System.out.println(processar.getNome());
				
				interacao.remove();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
