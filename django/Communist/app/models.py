# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class User(models.Model):
    username = models.CharField(unique=True, max_length=128, db_comment='学号')
    name = models.CharField(max_length=20, db_comment='姓名')
    gender = models.CharField(max_length=1, db_comment='性别')
    password = models.CharField(max_length=30, db_comment='身份证号')
    communist = models.CharField(max_length=10, db_comment='是否提交入党申请书')

    def __str__(self):
        return 'username %s' % self.username

    class Meta:
        managed = False
        db_table = 'user'
