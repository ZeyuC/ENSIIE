
# coding: utf-8

# In[1]:


import numpy


# In[2]:


# matrice vide

numpy.empty([3,2])


# In[3]:


numpy.zeros(7)


# In[4]:


numpy.random.poisson(5,13)


# In[13]:


X1 = numpy.random.randint(-5,7,[4,3])


# In[14]:


X1.T


# In[15]:


v=numpy.array([2,1,5])


# In[16]:


v


# In[17]:


v.shape


# In[19]:


v2=numpy.array([[1,5,6],[0,14,17]])
v2


# In[20]:


v2.shape


# In[21]:


v2[1,1]


# In[22]:


v3=numpy.random.randint(-5,10,size=[3,5])
v3


# In[24]:


X1


# In[25]:


S1 = numpy.sum(X1,axis=0)


# In[26]:


S1


# In[28]:


S2 = numpy.sum(X1,axis=1)
S2


# In[32]:


m1 = numpy.mean(X1,axis=0) 
m1


# In[36]:


X5= numpy.random.poisson(7,31)
X5


# In[37]:


X5a=X5[:10]
X5a


# In[38]:


for i in range(11):
    print("i=",i)


# In[39]:


for i in range(2,11):
    print("i=",i)


# In[49]:


X6 = numpy.random.normal(7,4,41)
X6

import random 
X7 = [3+(7-3)*random.random() for _ in range(14)]
X7


# In[57]:


def statsl(data):
    import numpy 
    return numpy.mean(data),numpy.var(data)

p=statsl(X7)
p[0]
p[1]


# In[61]:



X10 = numpy.random.randint(-5,6,[3,3])

D=numpy.linalg.det(X10)
print('esperance = %.4f'%p[0])


# In[62]:


A=numpy.matrix([[3,-2,1],[-1,4,9],[5,-7,3]])


# In[63]:


B=numpy.matrix([[2],[11],[17]])
R = numpy.linalg.solve(A,B)
R

