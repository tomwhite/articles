d <- read.csv('/home/tom/ch.csv');
attach(d)

png('/home/tom/ch-graph.png')
plot(replicas, sd, log="xy")
abline(lm(log10(sd)~log10(replicas)), col="blue")
dev.off()