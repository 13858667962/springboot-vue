<template>
  <div style="width: calc(100vh - 50px);">
    <!--    编辑、新增、删除按钮,独占一行-->
    <div style="height: 10vh;margin:10px;border: solid aliceblue ;padding: 18px;background:aliceblue ">
      <el-button style="margin-right: 20px" type="primary" @click="dialogFormVisible=true">新增</el-button>
      <!--     删除时再次确认框-->
      <el-popconfirm
          confirm-button-text='好的'
          cancel-button-text='不用了'
          icon="el-icon-info"
          icon-color="red"
          title="删除选中数据吗？"
          @confirm="deleteSelect"
      >
        <el-button slot="reference" type="primary" style="margin-right: 20px">删除</el-button>
      </el-popconfirm>
      <!--      批量审核按钮-->
      <el-button style="margin-right: 10px" type="primary">审核</el-button>
      <!--      批量批准按钮-->
      <el-button style="margin-right: 10px" type="primary">批准</el-button>
      <!--      //按照销售订单号进行查询-->
      <el-button style="float: right;margin-right: 10px" type="primary" @click="selectById">查找</el-button>
      <el-input style="float: right;width: 300px;margin-right: 10px" v-model="input" placeholder="请输入内容"></el-input>
    </div>
    <!--销售主表-->
    <div style="display: flex;margin-top: 10px">
      <div style="float: left;width: 48%;margin:10px 10px;height: 75vh;">
        <!--        分页器-->
        <el-table
            :row-key="getRowKeys"
            height="600px"
            ref="multipleTable"
            style="width:auto"
            border
            @selection-change="handleSelectionChange"
            :data="tableData"
        >
<!--          .slice((currentPage-1)*pageSize,currentPage*pageSize)-->
          <el-table-column
              :reserve-selection="true"
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              sortable
              prop="saleOrderCode"
              label="销售订单号"
          >
          </el-table-column>
          <el-table-column
              prop="saleOrderType"
              label="销售订单类型"
          >
          </el-table-column>
          <el-table-column
              :filter-multiple="true"
              :filters="filterData"
              :filter-method="filterHandler"
              filter-placement="bottom-end"
              prop="signDate"
              label="签订日期"
              width="120px">
          </el-table-column>
          <el-table-column
              prop="clientId"
              label="客户编码">
          </el-table-column>

          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                  type="text"
                  @click="handleEdit(scope.row)">编辑
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" style="margin-top:15px;width: auto; "align="center" >
          <el-pagination @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[5,10,20,max]"
                         :page-size="pageSize"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="total">
          </el-pagination>
        </div>
      </div>

      <!--      销售明细表-->
      <div style="float: right;width:48%;margin:10px 10px;height: 100%">
        <el-table
            :data="tableData2"
            style="width: 100%"
            stripe
            border
            height="650px">
          <el-table-column
              sortable
              prop="saleOrderCode"
              label="销售订单号"
          >
          </el-table-column>
          <el-table-column
              prop="productId"
              label="产品编号"
          >
          </el-table-column>
          <el-table-column
              prop="deliveryDate"
              label="交货日期">
          </el-table-column>
          <el-table-column
              prop="soQuantity"
              label="订购数量">
          </el-table-column>
          <el-table-column
              prop="price"
              label="价格">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址">
          </el-table-column>
        </el-table>
      </div>
    </div>

    <!--    点击新增按钮弹出的form表单-->
    <el-dialog v-model="dialogFormVisible" :visible.sync="dialogFormVisible" title="新增" width="60%" append-to-body>
      <el-form :model="form" :rules="rules" ref="form" label-width="120px" style="width: 100%" class="demo-form">
        <el-row>
          <el-col :span="8">
            <el-form-item label="销售订单号" prop="saleOrderCode" >
              <el-input v-model="form.saleOrderCode" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="销售订单类型" prop="saleOrderType">
              <el-select v-model="form.saleOrderType">
                <el-option value="1"></el-option>
                <el-option value="2"></el-option>
                <el-option value="3"></el-option>
                <el-option value="4"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="签订日期" prop="signDate">
              <el-date-picker
                  value-format="yyyy-MM-dd"
                  v-model="form.signDate"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                  v-model="form.deliveryDate"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编号" prop="productId">
              <el-input v-model="form.productId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="客户编码" prop="clientId">
              <el-input v-model="form.clientId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="订购数量" prop="soQuantity">
              <el-input v-model="form.soQuantity"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="price">
              <el-input v-model="form.price"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="地址" prop="soAddress">
          <el-input v-model="form.soAddress" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="add('form')">确 定</el-button>
  </span>
    </el-dialog>

    <!--    点击编辑时跳出的编辑表单-->
    <el-dialog v-model="editFormVisible" :visible.sync="editFormVisible" title="编辑" width="60%" append-to-body>
      <el-form :model="edit" :rules="editRules" ref="edit" label-width="120px" style="width: 100%">
        <el-row>
          <el-col :span="8">
            <el-form-item label="销售订单号" prop="saleOrderCode">
              <el-input v-model="edit.saleOrderCode" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="销售订单类型" prop="saleOrderType">
              <el-select v-model="form.saleOrderType">
                <el-option value=1></el-option>
                <el-option value=2></el-option>
                <el-option value=3></el-option>
                <el-option value=4></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="签订日期" prop="signDate">
              <el-date-picker
                  value-format="yyyy-MM-dd"
                  v-model="edit.signDate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="交货日期" prop="deliveryDate">
              <el-date-picker
                  v-model="edit.deliveryDate"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="8">
            <el-form-item label="产品编号" prop="productId">
              <el-input v-model="edit.productId"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">

            <el-form-item label="客户编码" prop="clientId">
              <el-input v-model="edit.clientId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="订购数量" prop="soQuantity">
              <el-input v-model="edit.soQuantity"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="价格" prop="price">
              <el-input v-model="edit.price"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="地址" prop="soAddress">
          <el-input v-model="edit.soAddress" type="textarea"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="editFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="handleEditRow">确 定</el-button>
  </span>
    </el-dialog>

  </div>


</template>

<script>
import request from "../utils/request";

export default {
  name: "saleOrder",
  components: {},
  //计算属性

  data() {
    return {
      max:'',//分页，用于显示所有数据
      filterData:[],//日期筛选数据
      pageSize: 5,
      currentPage: 1,
      total:5,
      input: '',//input输入内容
      dialogFormVisible: false,//新增表单是否可视
      editFormVisible: false,//编辑表单是否可视
      tableData: [],//销售主表数据
      tableData2: [],//销售明细表数据
      form: {
        signDate:'',
        deliveryDate:'',
      },//form表单规则属性
      multipleSelection: [],//多选后的数据
      ids: [],//存放销售订单号的数组，作为参数请求后端
      rules: {
        signDate: [
          {required:true,message:"签订日期不能为空",trigger:"change"}
        ],
        deliveryDate:[
          {required:true,message:"交货日期不能为空",trigger:"change"},
        ]
      },//新增订单的规则
      edit: {
        signDate:'',
        deliveryDate:'',
      },//编辑表单规则属性
      editRules: {
        saleOrderCode: [
          {required:true, message: "订单号不能为空", trigger: "change"},
        ],
        signDate: [
          {required:true,message:"签订日期不能为空",trigger:"change"}
        ]
      }//编辑表单属性规则
    }
  },
  created() {
    this.load();
    },

  methods: {
    //新增按钮绑定的方法，先判断数据是否有效，在进行添加请求
    add(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.form)
          request.post("/api/order/insert", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "添加成功"
              })
              this.load()
              this.dialogFormVisible = false
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    //页面加载时调用该方法，查询数据库所有数据
    load() {
      request.get("/api/order/list",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          input:this.input,
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.total=res.data.total
        this.clearFilterData();
        this.getFilterData();
        this.deWeight(this.filterData)
      })
      this.getNumber();
    },
    //绑定删除按钮，批量删除
    deleteSelect() {
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.ids.push(this.multipleSelection[i].saleOrderCode)
      }
      console.log(this.ids)
      request.post("api/order/delete", this.ids).then(res => {
        this.load()
      })
    },
    //多选框选中后明细表显示选中数据
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.tableData2 = this.multipleSelection;
    },
    //原来是绑定查询按钮，现有已经取消绑定，该方法以无用
    selectById() {
      request.get("api/order/selectById/" + this.input).then(res => {
        if (res.code === '0') {
          this.tableData = res.data
          this.$message({
            type: "success",
            message: "查询成功"
          })
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
    },
    //改变每页的容纳页数时
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.load();
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum;
      this.load();
    },

    getRowKeys(row) {
      return row.saleOrderCode;
    },
    //绑定各行编辑按钮，按照销售订单号进行修改
    handleEdit(row) {
      request.get("/api/order/selectById/"+row.saleOrderCode).then(res=>{
        this.edit=res.data[0];
      });
      this.editFormVisible=true;
    },
    //绑定edit表单中的确认按钮，按销售订单号进行修改
    handleEditRow(){
        request.put("/api/order/editById", this.edit).then(res => {
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "编辑成功"
            })
            this.editFormVisible = false;
            this.load()
          }
        })
    },
    //返回符合条件的数据
    // 参数分别是：选中的条件 、当前行 、当前列
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    // 数组对象去重
    deWeight(arr) {
      for (let i = 0; i < arr.length - 1; i++) {
        for (let j = i + 1; j < arr.length; j++) {
          if (arr[i].text === arr[j].text) {
            arr.splice(j, 1);
            j--;
          }
        }
      }
    },
    //获取筛选数据下拉框
    getFilterData(){
      for(let i=0;i<this.tableData.length;i++){
        this.filterData.push({
          text:this.tableData[i].signDate,
          value:this.tableData[i].signDate,
        })
      }
    },
    //清空筛选数据下拉框
    clearFilterData(){
      this.filterData=[];
    },
    //获取数据库总条数
    getNumber(){
      request.get("/api/order/number").then(res=>{
        this.max=res.data;
      })
    }
  },
}
</script>

<style scoped>

</style>