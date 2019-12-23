package View;

import IO.CSVHandler;
import IO.ReportHandler;
import ML.RandomTreeClassifier;
import weka.core.Instances;

public class TableView {
    RandomTreeClassifier timeClassifierModel;
    RandomTreeClassifier randomTreeDayClassifier;


    public TableView() {
        timeClassifierModel = new RandomTreeClassifier(48);
        timeClassifierModel.train("time_train.csv");
        randomTreeDayClassifier = new RandomTreeClassifier(1);
        randomTreeDayClassifier.train("day_train.csv");
        CSVHandler.createDayTestCSV();
        CSVHandler.createTimeTestCSV();
    }

//    public NaiveBayesClassifier getTimeClassifierModel() {
//        return timeClassifierModel;
//    }
//
//    public RandomTreeClassifier getRandomTreeDayClassifier() {
//        return randomTreeDayClassifier;
//    }

    public Instances getTimeData() {
        return timeClassifierModel.predictTime("time_test.csv");
    }

    public Instances getDayData() {
        return randomTreeDayClassifier.predictDay("day_test.csv");
    }

    public String report() {
        return ReportHandler.read();
    }
}
