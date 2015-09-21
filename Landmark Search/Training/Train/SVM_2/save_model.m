function save_model(model, file_name)

fileId = fopen(file_name, 'w');
if model.Parameters(1) == 0
    fprintf(fileId, 'svm_type %s\n', 'c_svc');
elseif model.Parameters(1) == 1
    fprintf(fileId, 'svm_type %s\n', 'nu_svc');
end

if model.Parameters(2) == 0
    fprintf(fileId, 'kernel_type %s\n', 'linear');
else
    if model.Parameters(2) == 2
        fprintf(fileId, 'kernel_type %s\n', 'rbf');
    elseif model.Parameters(2) == 4
        fprintf(fileId, 'kernel_type %s\n', 'precomputed');
    end
end

if model.Parameters(2) == 2
    fprintf(fileId, 'gamma %g\n', model.Parameters(4));
end

fprintf(fileId, 'nr_class %d\n', model.nr_class);
fprintf(fileId, 'total_sv %d\n', model.totalSV);

fprintf(fileId, 'rho');
fprintf(fileId, ' %g', model.rho);
fprintf(fileId, '\n');

if (model.Label)
    fprintf(fileId, 'label');
    fprintf(fileId, ' %d', model.Label);
    fprintf(fileId, '\n');
end

if (model.ProbA)
    fprintf(fileId, 'probA');
    fprintf(fileId, ' %g', model.ProbA);
    fprintf(fileId, '\n');
end

if (model.ProbB)
    fprintf(fileId, 'probB');
    fprintf(fileId, ' %g', model.ProbB);
    fprintf(fileId, '\n');
end

if (model.nSV)
    fprintf(fileId, 'nr_sv');
    fprintf(fileId, ' %d', model.nSV);
    fprintf(fileId, '\n');
end

fprintf(fileId, 'SV\n');
sv_coef = model.sv_coef;
SVs = model.SVs;
for i = 1:model.totalSV
    fprintf(fileId, '%.16g ', sv_coef(i,:));
    
    if model.Parameters(1) == 4
        fprintf(fileId, '0:%d ', int8(SVs(i,:)));
    else
        [~, index, value] = find(SVs(i,:));
        fprintf(fileId, '%d:%.8g ',[index; value]);
    end
    fprintf(fileId, '\n');
end

fclose(fileId);