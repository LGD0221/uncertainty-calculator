package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private UncertaintyCalculator uncertaintyCalculator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // ��ʼ��
        uncertaintyCalculator = new UncertaintyCalculator();

        // ���� GUI ���
        Label titleLabel = new Label("������Ӣ�Ķ��ŷָ�:");
        TextField measurementsField = new TextField();
        Button calculateButton = new Button("����");
        Label resultLabel = new Label();

        // ���ü��㰴ť�Ĳ���
        calculateButton.setOnAction(e -> {
            String measurementsText = measurementsField.getText();
            List<Double> measurements = parseMeasurements(measurementsText);
            try {
                double uncertainty = uncertaintyCalculator.calculateUncertainty(measurements);
                resultLabel.setText("��ȷ����: " + uncertainty);
            } catch (IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
            }
        });

        // �������񴰸񲼾�
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(measurementsField, 1, 0);
        gridPane.add(calculateButton, 0, 1);
        gridPane.add(resultLabel, 1, 1);

        // ���ø�����Ĳ���
        VBox root = new VBox(gridPane);
        root.setPadding(new Insets(20));

        // ���ó�������ʾ��̨
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("��ȷ���ȼ�����");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Double> parseMeasurements(String measurementsText) {
        List<Double> measurements = new ArrayList<>();
        String[] measurementStrings = measurementsText.split(",");
        for (String measurementString : measurementStrings) {
            try {
                double measurement = Double.parseDouble(measurementString.trim());
                measurements.add(measurement);
            } catch (NumberFormatException e) {
                // ������Ч����ֵ
            }
        }
        return measurements;
    }
}
