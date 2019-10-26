package ML;

import IO.CSVHandler;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

import java.util.ArrayList;

public class NaiveBayesClassifier implements MyClassifier {

    NaiveBayes classifierModel;

    public void train(String fileName) {
        final Evaluation eval;
        try {
            Instances trainingSet = CSVHandler.read(fileName);
            if (trainingSet.classIndex() == -1) {
                trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
            }
            classifierModel = new NaiveBayes();

            classifierModel.buildClassifier(trainingSet);


            // Test the model
            eval = new Evaluation(trainingSet);
            eval.evaluateModel(classifierModel, trainingSet);


            // Print the result à la Weka explorer:
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toMatrixString());
            System.out.println(classifierModel.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instances predict(String fileName, int K) {
        Instances predictingSet = null;
        ArrayList<Double> predictedSet = new ArrayList<>();
        try {
            predictingSet = CSVHandler.read(fileName);
            for (int i = 0; i < predictingSet.numInstances(); i++) {
                try {
                    for (int n = 9; n < predictingSet.numAttributes(); n++) {
                        predictingSet.setClassIndex(n);
//                        if (String.valueOf(predictingSet.instance(i).classValue()).equals("NaN")) {
                        double label = classifierModel.classifyInstance(predictingSet.instance(i));
                        predictingSet.instance(i).setClassValue(label);
                        predictedSet.add(label);
//                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
//            System.out.println("Pre Dic ting SET");
//            for (int i = 0; i < predictingSet.numInstances(); i++) {
//                System.out.println(predictingSet.instance(i));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictingSet;
    }

    @Override
    public Classifier getClassifier() {
        return classifierModel;
    }
}
