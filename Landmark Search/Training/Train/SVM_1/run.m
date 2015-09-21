function run()
clear all, close all
% =========== Parameters =============
train_file = 'train_file.txt';
test_file = 'train_file.txt';

kernel = 'linear';
folds = 10;
% neu dung kernel linear, nen chinh cv_range thanh [0 20; 2 7] de co do
% chinh xac cao hon
cv_range = [5 15; 2 7];

model_file = 'model.mat';
log_file = 'log.txt';

% ====================================o

train_set = importdata(train_file);
train_label = train_set(:,1);
train_set = train_set(:,2:end);

test_set = importdata(test_file);
test_label = test_set(:,1);
test_set = test_set(:,2:end);

min_label = min([train_label; test_label]);
max_label = max([train_label; test_label]);

% train model
[model, cv_acc, training_acc, training_time] = cv_svm_train(train_label, train_set, kernel, folds, cv_range);
save(model_file, 'model', 'cv_acc', 'training_acc', 'training_time');

log = cell(max_label, 1);
% test
for i = min_label:max_label
    class_idx = test_label == i;
    n_test = sum(class_idx);
    test_data = test_set(class_idx,:);
    wrong_checklist = zeros(n_test, 1);
    for j = 1:n_test
        [~, test_acc] = svmpredict(i, test_data(j,:), model);
        if test_acc(1) == 0
            wrong_checklist(j) = 1;
        end
    end
    log{i} = wrong_checklist;
end

fileId = fopen(log_file, 'at');
fprintf(fileId, '\n=============================\n');
fprintf(fileId, 'Test datetime: %s\n', datestr(now));
fprintf(fileId, 'Training time (sec): %.5g\n', training_time);
fprintf(fileId, 'Training accuracy: %.5g\n', training_acc(1));
fprintf(fileId, 'Cross validation accuarcy: %.5g\n', cv_acc);
fprintf(fileId, 'Testing result:\n');
for i = min_label:max_label
    total = numel(log{i});
    wrong = sum(log{i});
    fprintf(fileId, 'Class %d: Total(%d) Right(%d) Wrong(%d) Wrong-Checklist: ',...
        i, total, total-wrong, wrong);
    fprintf(fileId, '%d ', log{i});
    fprintf(fileId, '\n');
end
fprintf(fileId, '=============================\n');
fclose(fileId);

clear all;