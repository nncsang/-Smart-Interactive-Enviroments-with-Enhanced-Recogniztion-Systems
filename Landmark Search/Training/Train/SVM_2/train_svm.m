function train_svm()
% ============ Parameters ============
% output mat file of kmeans
svm_material = 'D:\ZuBu\Encoder\encoder_32_0.5_0.7_0.3_0.1.txt';

model_txt = 'D:\ZuBu\Model\model_32_0.5_0.7_0.3_0.1_g.txt';
stats_txt = 'D:\ZuBu\Stats\svm-stats_32_0.5_0.7_0.3_0.1_g.txt';
kernel = 'gaussian';
folds = 10;
cv_range = [5 15; 2 7];
% ====================================

% load(svm_material);
bof = importdata(svm_material);
labels = bof(:,1);
bof(:,1) = [];
[model, cv_acc, training_acc, training_time] = cv_svm_train(...
            labels, bof, kernel, folds, cv_range);
save_model(model, model_txt);

fileId = fopen(stats_txt, 'w');
fprintf(fileId, 'Cross validation accuracy: %.3g\n', cv_acc);
fprintf(fileId, 'Training accuracy: %.3g\n', training_acc(1));
fprintf(fileId, 'Training time (sec): %.3g', training_time);
fclose(fileId);
