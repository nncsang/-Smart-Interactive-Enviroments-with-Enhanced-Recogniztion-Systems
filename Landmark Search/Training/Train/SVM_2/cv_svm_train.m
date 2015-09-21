function [model, cv_acc, training_acc, exc_time] =...
         cv_svm_train(...
                 labels,...         % label
                 training_set,...   % training vectors
                 kernel,...         % kernel function: linear or gaussian
                 folds,...          % number of folds in cross validation
                 range...           % range for searching params
                 )
%# start timer
tic;
n_point = 25;
cv_acc = 0;
cv_accs = zeros(n_point,1);

center = sum(range, 2)/2;
step = center - range(:,1);
is_first_res = true;

%# apply desire kernel
if strcmp(kernel, 'gaussian') % gaussian kernel
    while true
        if is_first_res
            [C, gamma] = meshgrid(center(1) - step(1) : step(1)/2 : center(1) + step(1),...
                                center(2) - step(2) : step(2)/2 : center(2) + step(2));
            is_first_res = false;

            %# grid seach & cross validation
            for i = 1:numel(C)
                % full version
                % tmp = svmtrain(labels, training_set, ...
                % sprintf('-c %f -g %f -q -v %d', 2^C(i), 2^gamma(i), folds));
                % if numel(tmp) > 0
                %     cv_accs(i) = tmp;
                % else
                %     cv_accs(i) = 0;
                % end

                % fast version
                cv_accs(i) = svmtrain(labels, training_set, ...
                    sprintf('-c %f -g %f -q -v %d', 2^C(i), 2^gamma(i), folds));
            end
        else
            [C, gamma] = meshgrid([center(1) - step(1)/2, center(1), center(1) + step(1)/2],...
                                [center(2) - step(2)/2, center(2), center(2) + step(2)/2]);
            cv_accs(5) = cv_acc;

            %# grid seach & cross validation
            for i = [1:4,6:9]
                % full version
                % tmp = svmtrain(labels, training_set, ...
                % sprintf('-c %f -g %f -q -v %d', 2^C(i), 2^gamma(i), folds));
                % if numel(tmp) > 0
                %     cv_accs(i) = tmp;
                % else
                %     cv_accs(i) = 0;
                % end

                % fast version
                cv_accs(i) = svmtrain(labels, training_set, ...
                    sprintf('-c %f -g %f -q -v %d', 2^C(i), 2^gamma(i), folds));
            end
        end

        %# find the best pair of params
        [cv_acc, idx] = max(cv_accs);

        %# shrink the range
        step = step/2;
        if(max(step) < 1)
            break;
        end

        %# move the center
        center = [C(idx); gamma(idx)];
    end
    %# train model using the best pair of params
    model = svmtrain(labels, training_set, ...
            sprintf('-c %f -g %f -q', 2^C(idx), 2^gamma(idx)));
        
elseif strcmp(kernel, 'linear') % linear kernel
	while true
        if is_first_res
            C = center(1) - step(1) : step(1)/2 : center(1) + step(1);
            is_first_res = false;

            for i = 1:numel(C)
                % full version
                %tmp = svmtrain(labels, training_set, ...
                %    sprintf('-t 0 -c %f -q -v %d', 2^C(i), folds));
                %if numel(tmp) > 0
                %    cv_accs(i) = tmp;
                %else
                %    cv_accs(i) = 0;
                %end

                % fast version
                cv_accs(i) = svmtrain(labels, training_set, ...
                    sprintf('-t 0 -c %f -q -v %d', 2^C(i), folds));
            end
        else
            C = [center(1) - step(1)/2, center(1), center(1) + step(1)/2];
            cv_accs(2) = cv_acc;

            for i = [1,3]
                % full version
                %tmp = svmtrain(labels, training_set, ...
                %    sprintf('-t 0 -c %f -q -v %d', 2^C(i), folds));
                %if numel(tmp) > 0
                %    cv_accs(i) = tmp;
                %else
                %    cv_accs(i) = 0;
                %end

                % fast version
                cv_accs(i) = svmtrain(labels, training_set, ...
                    sprintf('-t 0 -c %f -q -v %d', 2^C(i), folds));
            end
        end

        %# find the best pair of params
        [cv_acc, idx] = max(cv_accs);

        %# shrink the range
        step = step/2;
        if(step(1) < 1)
            break;
        end

        %# move the center
        center = C(idx);
    end
    %# train model using the best pair of params
    model = svmtrain(labels, training_set, ...
            sprintf('-t 0 -c %f -q', 2^C(idx)));  
end

%# calculate training error
[~, training_acc] = svmpredict(labels, training_set, model);

%# execution time
exc_time = toc;