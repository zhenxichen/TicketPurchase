<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="始发站" prop="start">
        <el-select v-model="queryParams.start" placeholder="请选择始发站">
          <el-option
            v-for="item in stationList"
            :key = "item.stationId"
            :label="item.stationName"
            :value="item.stationId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="终点站" prop="dest" placeholder="请选择终点站">
        <el-select v-model="queryParams.dest">
          <el-option
            v-for="item in stationList"
            :key = "item.stationId"
            :label="item.stationName"
            :value="item.stationId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bus:bus:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bus:bus:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bus:bus:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['bus:bus:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="busList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车次" align="center" prop="busId" />
      <el-table-column label="始发站编号" align="center" prop="start" />
      <el-table-column label="终点站编号" align="center" prop="dest" />
      <el-table-column label="发车时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到达时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="间隔天数" align="center" prop="day" />
      <el-table-column label="默认座位数" align="center" prop="seat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bus:bus:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bus:bus:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-date"
            @click="handleTicket(scope.row)"
          >票量</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车次管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车次" prop="busId">
          <el-input placeholder="请输入车次"
            v-model="form.busId"
            :disabled="disableBusId"
          />
        </el-form-item>
        <el-form-item label="始发站" prop="start">
          <el-select v-model="form.start" placeholder="请选择始发站">
            <el-option v-for="item in stationList"
              :key = "item.stationId"
              :label="item.stationName"
              :value="item.stationId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="终点站" prop="dest">
          <el-select v-model="form.dest" placeholder="请选择终点站">
            <el-option v-for="item in stationList"
              :key = "item.stationId"
              :label="item.stationName"
              :value="item.stationId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="发车时间" prop="startTime">
          <el-time-picker clearable size="small"
            v-model="form.startTime"
            type="time"
            value-format="HH:mm:ss"
            placeholder="选择发车时间">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="到达时间" prop="endTime">
          <el-time-picker clearable size="small"
            v-model="form.endTime"
            type="time"
            value-format="HH:mm:ss"
            placeholder="选择到达时间">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="间隔天数" prop="day">
          <el-input v-model="form.day" placeholder="请输入间隔天数" />
        </el-form-item>
        <el-form-item label="默认座位数" prop="seat">
          <el-input v-model="form.seat" placeholder="请输入默认座位数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 车票量设定对话框 -->
    <el-dialog title="车票量设定" :visible.sync="ticket.visible" width="700px" append-to-body>
      <el-form ref="ticketForm" :model="ticketForm" :rules="ticketRules" :inline="true">
        <el-form-item label="车次" prop="busId">
          <el-input v-model="ticketForm.busId" placeholder="请输入车次" disabled/>
        </el-form-item>
        <el-form-item label="日期" prop="busDate">
          <el-date-picker
            v-model="ticketForm.busDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发车日期" 
            @change="updateTicketInfo"/>
        </el-form-item>
        <el-form-item label="座位" prop="seat">
          <el-input-number v-model="ticketForm.seat" />
        </el-form-item>
        <br>
        <el-form-item label="员工票数量" prop="employeeTickets">
          <el-input-number v-model="ticketForm.employeeTickets" />
        </el-form-item>
        <el-form-item label="普通票数量" prop="normalTickets">
          <el-input-number v-model="ticketForm.normalTickets" />
        </el-form-item>
        <br>
        <el-form-item label="剩余员工票数量" prop="employeeTicketsRemain">
          <el-input-number v-model="ticketForm.employeeTicketsRemain" />
        </el-form-item>
        <el-form-item label="剩余普通票数量" prop="normalTicketsRemain">
          <el-input-number v-model="ticketForm.normalTicketsRemain" />
        </el-form-item>
        <br>
        <el-form-item label="员工票价格" prop="employeePrice">
          <el-input-number v-model="ticketForm.employeePrice" />
        </el-form-item>
        <el-form-item label="普通票价格" prop="normalPrice">
          <el-input-number v-model="ticketForm.normalPrice" />
        </el-form-item>
        <br>
        <el-form-item label="司机" prop="driver">
          <el-select placeholder="请选择司机" v-model="ticketForm.driver">
            <el-option v-for="item in driver"
              :key="item.id"
              :label="item.name"
              :value="item.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTicketForm">确 定</el-button>
        <el-button @click="cancelTicket">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBus, getBus, delBus, addBus, updateBus, exportBus, listStation, setTicket, driverList, getTicket } from "@/api/bus/bus";

export default {
  name: "Bus",
  components: {
  },
  data() {
    const destValidator = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请选择终点站'));
      } else if (value === this.form.start) {
        callback(new Error('始发站与终点站不能相同'));
      } else {
        callback();
      }
    };
    const testValidator = (rule, value, callback) => {
      console.log("value", value);
    }
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车次管理表格数据
      busList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        start: null,
        dest: null,
        startTime: null,
        endTime: null,
        day: null,
        seat: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        busId: [{ required: true, message: "车次不能为空", trigger: "blur" }],
        start: [{ required: true, message: "始发站编号不能为空", trigger: "blur" }],
        dest: [
          { required: true, message: "终点站编号不能为空", trigger: "blur" },
          { required: true, validator: destValidator, trigger: 'blur' }
        ],
        startTime: [
          { required: true, message: "发车时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "到达时间不能为空", trigger: "blur" }
        ],
        day: [
          { required: true, message: "间隔天数不能为空", trigger: "blur" }
        ],
      },
      // 车站列表
      stationList: [],
      oper: '',   // 当前操作（add或update）
      disableBusId: false,    // 是否可以修改busId
      // 车票量管理对话框
      ticket: {
        visible: false,
      },
      ticketForm: {
        busId: '',    // 车次
        busDate: undefined,   // 日期
        seat: undefined,      // 座位数
        employeeTickets: undefined,     // 员工票数量
        normalTickets: undefined,       // 普通票数量
        employeeTicketsRemain: undefined,   // 剩余员工票数量
        normalTicketsRemain: undefined,     // 剩余普通票数量
        employeePrice: undefined,           // 员工票价格
        normalPrice: undefined,             // 普通票价格
        driver: undefined,              // 司机
      },
      ticketRules: {
        busDate: [{ required: true, message: "日期不能为空" }],
        seat: [{ required: true, message: "座位数不能为空", trigger: "blur" }],
        employeeTickets: [{ required: true, message: "员工票数量不能为空", trigger: "blur" }],
        normalTickets: [{ required: true, message: "普通票数量不能为空", trigger: "blur" }],
        employeeTicketsRemain: [{ required: true, message: "剩余员工票数量不能为空", trigger: "blur" }],
        normalTicketsRemain: [{ required: true, message: "剩余普通票数量不能为空", trigger: "blur" }],
        employeePrice: [{ required: true, message: "员工票价格不能为空", trigger: "blur" }],
        normalPrice: [{ required: true, message: "普通票价格不能为空", trigger: "blur" }],
        driver: [{ required: true, message: "司机不能为空", trigger: "blur" }]
      },
      driver: [],
    };
  },
  created() {
    this.getList();
    this.getDriverList();
  },
  methods: {
    /** 查询车次管理列表 */
    getList() {
      this.loading = true;
      listBus(this.queryParams).then(response => {
        this.busList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listStation().then(response => {
        this.stationList = response.data;
        console.log(response);
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        busId: null,
        start: null,
        dest: null,
        startTime: null,
        endTime: null,
        day: null,
        seat: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      console.log(this.queryParams);
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.busId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.oper = 'add';
      this.disableBusId = false;
      this.title = "添加车次管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.oper = 'update';
      this.disableBusId = true;
      const busId = row.busId || this.ids
      getBus(busId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车次管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.oper === 'update') {
            updateBus(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBus(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const busIds = row.busId || this.ids;
      this.$confirm('是否确认删除车次管理编号为"' + busIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBus(busIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有车次管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportBus(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    },
    handleTicket(row) {
      this.resetTicketForm();
      this.ticketForm.busId = row.busId;
      this.ticket.visible = true;
      console.log(this.ticket);
    },
    resetTicketForm() {
      this.ticketForm = {
        busId: '',
        busDate: undefined,
        seat: undefined,
        employeeTickets: undefined,
        normalTickets: undefined,
        employeeTicketsRemain: undefined,
        normalTicketsRemain: undefined,
        employeePrice: undefined,
        normalPrice: undefined,
      }
    },
    submitTicketForm() {
      const that = this;
      this.$refs["ticketForm"].validate(valid => {
        console.log(valid);
        if (valid) {
          setTicket(this.ticketForm)
            .then(response => {
              that.msgSuccess("设置成功");
              that.resetTicketForm();
              that.ticket.visible = false;
            });
        }
      })
    },
    cancelTicket() {
      this.resetTicketForm();
      this.ticket.visible = false;
    },
    getDriverList() {
      const that = this;
      driverList().then(response => {
        that.driver = response.data;
      })
    },
    // 更新车票量信息
    updateTicketInfo() {
      const that = this;
      const params = {
        busId: this.ticketForm.busId,
        date: this.ticketForm.busDate
      };
      getTicket(params).then(res => {
        console.log(res);
        if (res.data !== null) {
          that.ticketForm.seat = res.data.seat;
          that.ticketForm.employeeTickets = res.data.employeeTickets;
          that.ticketForm.normalTickets = res.data.normalTickets;
          that.ticketForm.employeeTicketsRemain = res.data.employeeTicketsRemain;
          that.ticketForm.normalTicketsRemain = res.data.normalTicketsRemain;
          that.ticketForm.employeePrice = res.data.employeePrice;
          that.ticketForm.normalPrice = res.data.normalPrice;
          that.ticketForm.driver = res.data.driver;
        }
      })
    }
  }
};
</script>
