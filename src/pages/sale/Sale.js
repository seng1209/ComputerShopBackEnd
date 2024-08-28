import { useState, useEffect } from "react";
import axios from "axios";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import "./Sale.css";

import DeleteSale from "./DeleteSale";

function BasicExample() {
  const [sales, setSales] = useState([]);

  useEffect(() => {
    laodSales();
  }, []);

  const laodSales = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/sales`);
      setSales(result.data);
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <div>
      <div className="sale-contain">
        <h1>Sale</h1>
        <Button href="/create_sale">New</Button>
      </div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Sale Date</th>
            <th>Customer Name</th>
            <th>Staff Name</th>
            <th>Total Amount</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {sales.map((sale, index) => {
            return (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>
                  {new Date(sale.saleDate)
                    .toISOString()
                    .slice(0, 19)
                    .replace("T", " ")}
                </td>
                <td>{sale.customer.name}</td>
                <td>{sale.staff.name}</td>
                <td>{sale.totalAmount}</td>
                <td>
                  <Button href={`/update_sale/${sale.uuid}`} variant="warning">
                    Edit
                  </Button>
                  {"   "}
                  <DeleteSale uuid={sale.uuid} />
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
}

export default BasicExample;
