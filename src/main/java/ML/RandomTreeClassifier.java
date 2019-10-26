package ML;

import IO.CSVHandler;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;

public class RandomTreeClassifier implements MyClassifier {
    RandomTree tree;

    public RandomTree getTree() {
        return tree;
    }

    public void train(String fileName) {
        final Evaluation eval;

        try {
            Instances trainingSet = CSVHandler.read(fileName);
            if (trainingSet.classIndex() == -1) {
                trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
            }
            tree = new RandomTree();

            tree.buildClassifier(trainingSet);
            tree.setKValue(0);
            tree.setMinNum(0);


            // Test the model
            eval = new Evaluation(trainingSet);
            eval.evaluateModel(tree, trainingSet);


            // Print the result à la Weka explorer:
//            System.out.println(eval.toSummaryString());
//            System.out.println(eval.toMatrixString());
//            System.out.println(tree.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Instances predict(String fileName, int K) {
        Instances predictingSet = null;
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("C:\\Users\\Saeed\\Documents\\Graduation Final Project\\Presence_Detection_Prediction\\" + fileName));
            predictingSet = loader.getDataSet();
            for (int i = 0; i < predictingSet.numInstances(); i++) {
                for (int n = K; n > 0; n--) {
                    predictingSet.setClassIndex(predictingSet.numAttributes() - n);
                    double label = tree.classifyInstance(predictingSet.instance(i));
                    predictingSet.instance(i).setClassValue(label);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictingSet;
    }

    @Override
    public RandomTree getClassifier() {
        return tree;
    }
}
