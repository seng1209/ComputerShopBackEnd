import React, { useState, useEffect } from "react";
import axios from "axios";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import "./Import.css";

const Import = () => {
  const [imports, setImports] = useState([]);

  useEffect(() => {
    loadImports();
  }, []);

  const loadImports = async () => {
    try {
      const result = await axios.get(`http://localhost:8080/api/v1/imports`);
      setImports(result.data);
      // console.log(result.data);
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <div>
      <div className="import-contain">
        <h1>Import</h1>
        <Button variant="primary" href="/create_import">
          New
        </Button>
      </div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Import Date</th>
            <th>Supplier</th>
            <th>Staff</th>
            <th>Total Amount</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {imports.map((i, index) => {
            return (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>
                  {new Date(i.importDate)
                    .toISOString()
                    .slice(0, 19)
                    .replace("T", " ")}
                </td>
                <td>{i.supplier.name}</td>
                <td>{i.staff.name}</td>
                <td>{i.totalAmount}</td>
                <td>
                  <Button href={`/update_import/${i.uuid}`} variant="warning">
                    Edit
                  </Button>
                  {"   "}
                  <Button variant="danger">Delete</Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

export default Import;
