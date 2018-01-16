load('../results/ff.txt');
x=ff(:,2);
y=ff(:,1);
[C,ia,idx] = unique(x,'stable');
val = accumarray(idx,y,[],@mean);
range = [0:0.1:max(x)];
your_vals = interp1(C,val,range,'linear','extrap');

figure;
plot(x,y,'b*',range,your_vals,'r+', 'markers',10);
title('Log FF plot of training data', 'FontSize', 24);
xlabel('log C', 'FontSize', 24);
ylabel('log Nc', 'FontSize', 24);
xt = get(gca, 'XTick');
set(gca, 'FontSize', 16);
yt = get(gca, 'YTick');
set(gca, 'FontSize', 16);
legend({'log Nc and log c', 'fitted line'} ,'FontSize',24);
savefig('../results/ff_plot.eps');
