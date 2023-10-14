import controller.WordCounterController;
import view.WordCounterView;

public class WordCounterApp {
    public static void main(String[] args) {
        // Criação de uma instância do controlador para gerenciar a lógica do aplicativo.
        WordCounterController controller = new WordCounterController();
        
        // Criação de uma instância da visualização (interface do usuário) do aplicativo.
        WordCounterView view = new WordCounterView();
        
        // Define o controlador na visualização para permitir a comunicação entre as partes.
        view.setController(controller);
        
        // Inicializa a interface do usuário e inicia o aplicativo.
        view.initialize();
    }
}
