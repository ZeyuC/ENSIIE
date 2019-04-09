complet_put=read('result/Complet_put.txt',502,1)
complet_call=read('result/Complet_call.txt',502,1)
reduit_put=read('result/Reduit_put.txt',502,1)
reduit_call=read('result/Reduit_call.txt',502,1)

scf(1);
clf(1);
xtitle("Complet_put", "i", "Cn");
x = linspace(0, 1000, 502);
plot(x,complet_put)
scf(2);
clf(2);
x = linspace(0, 1000, 502);
xtitle("reduit_put", "i", "Cn");
plot(x,reduit_put)


scf(3);
clf(3);
x = linspace(0, 1000, 500);
xtitle("erreur entre complet_put et reduit_put", "i", "erreur");
a = complet_put-reduit_put
plot(x,a(2:501))

scf(4);
clf(4);
x = linspace(0, 1000, 502);
xtitle("Complet_call", "i", "Cn");
plot(x,complet_call)

scf(5);
clf(5);
x = linspace(0, 1000, 502);
xtitle("reduit_call", "i", "Cn");
plot(x,reduit_call)


scf(6);
clf(6);
x = linspace(0, 1000, 500);
xtitle("erreur entre complet_call et reduit_call", "i", "erreur");
a = complet_call-reduit_call
plot(x,a(2:501))


