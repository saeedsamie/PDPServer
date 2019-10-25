package ML;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.util.ArrayList;

public class NaiveBayesClassifier implements MyClassifier {

    NaiveBayes classifierModel;

    public void train(String fileName) {
        final Evaluation eval;
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName));
//            loader.
            Instances trainingSet = loader.getDataSet();
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
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\PDPServer\\" + fileName));
            predictingSet = loader.getDataSet();
            for (int i = 0; i < predictingSet.numInstances(); i++) {
                try {
                    for (int n = 9; n < predictingSet.numAttributes(); n++) {
                        predictingSet.setClassIndex(n);
                        double label = classifierModel.classifyInstance(predictingSet.instance(i));
                        predictingSet.instance(i).setClassValue(label);
                        predictedSet.add(label);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Pre Dic ting SET");
            for (int i = 0; i < predictingSet.numInstances(); i++) {
                System.out.println(predictingSet.instance(i));
            }
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
