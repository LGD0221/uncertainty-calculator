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
        // 初始化
        uncertaintyCalculator = new UncertaintyCalculator();

        // 创建 GUI 组件
        Label titleLabel = new Label("数据用英文逗号分隔:");
        TextField measurementsField = new TextField();
        Button calculateButton = new Button("计算");
        Label resultLabel = new Label();

        // 设置计算按钮的操作
        calculateButton.setOnAction(e -> {
            String measurementsText = measurementsField.getText();
            List<Double> measurements = parseMeasurements(measurementsText);
            try {
                double uncertainty = uncertaintyCalculator.calculateUncertainty(measurements);
                resultLabel.setText("不确定度: " + uncertainty);
            } catch (IllegalArgumentException ex) {
                resultLabel.setText(ex.getMessage());
            }
        });

        // 设置网格窗格布局
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(measurementsField, 1, 0);
        gridPane.add(calculateButton, 0, 1);
        gridPane.add(resultLabel, 1, 1);

        // 设置根窗格的布局
        VBox root = new VBox(gridPane);
        root.setPadding(new Insets(20));

        // 设置场景，显示舞台
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setTitle("不确定度计算器");
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
                // 忽略无效测量值
            }
        }
        return measurements;
    }
}
