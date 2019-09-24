package dad.javafx.adivin.app;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private TextField nombreText;
	private Button saludarButton;
	private Label saludoLabel;
	private int numeroAleatorio;
	private int numeroDeErrores;

	@Override
	public void start(Stage primaryStage) throws Exception {
		numeroAleatorio = generateNumber();
		nombreText = new TextField();
		nombreText.setPromptText("Introduce número");
		nombreText.setMaxWidth(150);

		saludoLabel = new Label("Introduce un número del 1 al 100");
		saludarButton = new Button("Comprobar");
		saludarButton.setDefaultButton(true);
		saludarButton.setOnAction(e -> onCheckNumber(e));

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(saludoLabel, nombreText, saludarButton);
		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onCheckNumber(ActionEvent e) {
		int numero = 0;
		try {
			numero = Integer.parseInt(nombreText.getText());
			if (checkNumberCorrect(numero)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Has ganado");
				alert.setContentText(
						"Solo has necesitado: " + numeroDeErrores + " intentos \n" + "Vuelve a jugar y hazlo mejor.");
				numeroDeErrores = 0;
				numeroAleatorio = generateNumber();
				alert.showAndWait();
			} else if (mayorOmenor(numero)) {
				numeroDeErrores++;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Has fallado");
				alert.setContentText("El numero a adivinar es mayor a: " + numero + ". \n Vuelve a intentarlo.");

				alert.showAndWait();
			} else if (!mayorOmenor(numero)) {
				numeroDeErrores++;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Has fallado");
				alert.setContentText("El numero a adivinar es menor a: " + numero + ". \n Vuelve a intentarlo.");

				alert.showAndWait();
			}
		} catch (Exception e2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinError");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido");

			alert.showAndWait();
		}

	}

	private int generateNumber() {
		return (int) (Math.random() * 100 + 1);
	}

	private boolean checkNumberCorrect(int numero) {

		return (numero == numeroAleatorio ? true : false);
	}

	private boolean mayorOmenor(int numero) {
		boolean esMenor = false;
		if (numero < numeroAleatorio) {
			esMenor = true;
		}
		return esMenor;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
