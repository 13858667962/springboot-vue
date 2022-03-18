<template xmlns:display="http://www.w3.org/1999/xhtml">
  <div style="padding: 10px">

    <!--搜索去-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入内容" style="width: 20%;padding-right: 5px" clearable></el-input>
      <el-button type="primary" @click="load">查询</el-button>
    </div>

    <div style="width:23%;height:250px;background-color: #ff0000;display:inline-block" >
      <el-table
          :data="tableData"
          @row-click="shuachu"
          :show-overflow-tooltip="true"
          border
          display:inline-block
          height="250">
        <el-table-column
            fixed
            :show-overflow-tooltip="true"
            prop="productId"
            label="物料编号"
            width="100">
        </el-table-column>
        <el-table-column
            :show-overflow-tooltip="true"
            prop="productName"
            label="物料名称"
            width="150">
        </el-table-column>
      </el-table>
    </div>
    <div style="width:54%;height:250px;display:inline-block;margin-left: 2%" >
      <el-table
          :data="tableData2"
          border
          @row-click="shuachu2"
          display:inline-block
          height="250"
          >
        <el-table-column
            fixed
            prop="saleordercode"
            label="订单编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="productId"
            label="物料名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="clientId"
            label="顾客编号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="signdate"
            label="签订日期"
            width="150">
        </el-table-column>
        <el-table-column

            prop="Guanlianquantity"
            label="已关联数量"
            width="100">
        </el-table-column>
        <el-table-column

            prop="Guanlianquantity"
            label="关联数量"
            width="100">

          <template slot-scope="scope">

          </template>

        </el-table-column>


        <el-table-column

            prop="Guanlianquantity"
            label="计划缺少数"
            width="100">
        </el-table-column>
        <el-table-column
            prop="soquantity"
            label="数量"
            width="150">
        </el-table-column>
      </el-table>
    </div>
    <div style="width:19%;height:250px;display:inline-block;margin-left: 2%" >
      <el-table
          :data="tableData3"
          border
          display:inline-block
          height="250">
        <el-table-column
            fixed
            prop="delivery_date"
            label="交货期"
            width="100">
        </el-table-column>
        <el-table-column
            prop="guanlianquantity"
            label="交货数量"
            width="150">
        </el-table-column>
      </el-table>
    </div>

    <div style="height: 40px">
      <el-button type="primary" @click="addp" style="display:block;margin: 0 auto">添加</el-button>
    </div>

    <div style="width:79%;height:170px;background-color: red;display:inline-block;">
      <el-table
          :data="tableData4"
          border
          display:inline-block
          height=100%>
        <el-table-column
            fixed
            prop="woordercode"
            label="生产订单号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="productId"
            label="物料编号"
            width="150">
        </el-table-column>
        <el-table-column

            prop="wocustomquantity"
            label="定制数量"
            width="100">
        </el-table-column>
        <el-table-column

            prop="wocompletequantity"
            label="完工数量"
            width="100">
        </el-table-column>
        <el-table-column

            prop="womakedate"
            label="制定日期"
            width="150">
        </el-table-column>
        <el-table-column

            prop="wostartdate"
            label="执行日期"
            width="100">
        </el-table-column>
        <el-table-column

            prop="wostatus"
            label="状态"
            width="100">
        </el-table-column>

      </el-table>
    </div>

    <div style="width:19%;height:170px;display:inline-block;margin-left: 2%"></div>
  </div>



</template>

<script>
// @ is an alias to /src


import request from "../utils/request";

export default {
  name: 'productorder',
  components: {

  },
  data() {
    return {
      form:{},
      dialogVisible:false,
      currentPage:1,
      pageSize:200,
      search:'',
      total:'10',
      tableData: [{}],
      tableData2:[{}],
      tableData3:[{}],
      tableData4:[{}]
    }
  },
  /*页面刷新时就会调用*/
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/api/sp/list",{
        params:{
          search:this.search
        }
      }).then(res=>{
        console.log("SDFGHJHGFDSFGHJHGFDFGHJHGFDSGHJHGFDHJHG"+res)
        console.log("lala"+res,res.data)
        this.tableData=res.data
      })
    },

shuachu(row){
      console.log(row.productId)

  request.get("/api/saleorder/list/"+row.productId,{
    params:{
      search:this.search
    }
  }).then(res=>{
    console.log("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+res)
    console.log("lala"+res,res.data)
    this.tableData2=res.data
  });


  request.get("/api/saleorder/list2/"+row.productId,{
    params:{
      search:this.search
    }
  }).then(res=>{
    console.log("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+res)
    console.log("lala"+res,res.data)
    this.tableData3=res.data
  })

  request.get("/api/workorder/list/"+row.productId,{
    params:{
      search:this.search
    }
  }).then(res=>{
    console.log("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+res)
    console.log("lala"+res,res.data)
    this.tableData4=res.data
  })
},
    shuachu2(row){
      console.log(row.saleordercode)

      request.get("/api/saleorder/list3/"+row.saleordercode,{
        params:{
          search:this.search
        }
      }).then(res=>{
        console.log("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+res)
        console.log("lala"+res,res.data)
        this.tableData3=res.data
      })
    },

    userAdd(b){
      this.dialogVisible=true
      this.form={}
    },
    userSave(){
      if(this.form.id){request.put("/api/user/add",this.form).then(res=>{
        console.log(res)
        if(res.code==='0'){this.$message({
          type:"success",
          message:"更新成功"
        })}
        else{this.$message({
          type:"error",
          message:"res.msg"
        })}
        this.load()
        this.dialogVisible=false
      })}
      else{request.post("/api/user/add",this.form).then(res=>{
        console.log(res)
        if(res.code==='0'){this.$message({
          type:"success",
          message:"更新成功"
        })}
        else{this.$message({
          type:"error",
          message:"res.msg"
        })}
        this.load()
        this.dialogVisible=false
      })}

    },
    shanchu(id){
      request.delete("/api/user/delete/"+id).then(res=>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else {
          this.$message({
            type: "error",
            message: "res.msg"
          })
        }
        this.load()})

    },
    bianji(row){
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    /*改变每页个数触发*/
    handleSizeChange(pageSize){
      this.pageSize=pageSize,
          this.load()
    },
    /*改变当前页触发*/
    handleCurrentChange(pageNum){
      this.currentPage=pageNum,
          this.load()
    },
  }
}
</script>
