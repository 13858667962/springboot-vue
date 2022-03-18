<template>
  <div style="padding: 10px  " width="100%">
    <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
      <div style="height: 50px;line-height: 50px;padding-left: 10px;font-size: 14px">
        是否审核
        <el-select v-model="value" style="margin-left: 5px">
          <el-option
              v-for="item in options_1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
          状态
        <el-select v-model="value" style="margin-left: 5px">
          <el-option
              v-for="item in options_2"
              :key="item.wostatus"
              :label="item.label"
              :value="item.wostatus">
          </el-option>
        </el-select>
      </div >
      <div style="height: 50px;line-height: 50px;margin-left: 40px;width: auto">
        <el-input v-model="search" placeholder="请输生产订单号" style="width: 60%" clearable></el-input>
        <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      </div>
      <div style="height: 50px;line-height: 55px; margin-left: 100px">
        <el-date-picker
            v-model="value"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
        </el-date-picker>
        <el-button style="margin-left: 5px;margin-bottom: 5px">查询</el-button>
      </div>
    </div>
    <div style="margin: 10px 0">
      <el-table :data="tableData_1" border stripe style="width: 100%">
        <el-table-column prop="woordercode" label="生产订单号"> </el-table-column>
        <el-table-column prop="productid" label="产品编号" > </el-table-column>
        <el-table-column prop="wocustomquantity" label="定制数量"> </el-table-column>
        <el-table-column prop="wostatus" label="订单状态"> </el-table-column>
        <el-table-column prop="womakedate" label="制定日期"> </el-table-column>
        <el-table-column prop="wostartdate" label="执行日期"> </el-table-column>
        <el-table-column prop="woplancompletedate" label="计划完工日期"> </el-table-column>
        <el-table-column prop="wooperator" label="操作者"> </el-table-column>
      </el-table>
      <div style="margin: 10px 0">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5,10,20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>
    <div>
      <el-table :data="tableData_2" border stripe style="width: 100%">
        <el-table-column prop="woordercode" label="生产订单号"> </el-table-column>
        <el-table-column prop="woordcrode" label="销售订单号"> </el-table-column>
        <el-table-column prop="productid" label="产品编号" > </el-table-column>
        <el-table-column prop="wosoassignquantity" label="已关联数量" > </el-table-column>
      </el-table>
      <div style="margin: 10px 0">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5,10,20]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import request from "@/utils/request";

export default {
  name: 'ProductionOrderSchedule',
  components: {

  },
  created() {
    this.load();
  },
  methods: {
    load(){
      request.get("/api/productionorderschedule",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res =>{
        console.log(res)
        this.tableData_1 = res.data.records
        this.total= res.data.total
        // console.log("qqq" + res.data.records.wostatus)
        // if(res.data.records.wostatus){
        //   this.wostatus = "已完成"
        // }else {
        //   this.wostatus = "未完成"
        // }
      })
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleSizeChange(pageSize) {//改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.pageNum = this.currentPage
      this.load()
    }
  },
  data(){
    return{
      pageSize:10,
      search:'',
      currentPage:1,
      total:10,
      dialogVisible:false,
      form:{},
      tableData: [],
      options_2: [{
        wostatus: '0',
        label: '未完成'
      },
      {
        wostatus: '1',
        label: '已完成'
      },]
    }
  },
  setup() {
    return {
      input: ref(''),
    }
  },
}
</script>
